package com.dbp.pet_journey.usuario.domain;

import com.dbp.pet_journey.Exceptions.ResourceConflictException;
import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import com.dbp.pet_journey.cuidador.domain.Cuidador;
import com.dbp.pet_journey.mail.domain.EmailService;
import com.dbp.pet_journey.mail.model.Mail;
import com.dbp.pet_journey.mascota.domain.Mascota;
import com.dbp.pet_journey.mascota.domain.MascotaService;
import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaUpdateRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaUpdateResponseDto;
import com.dbp.pet_journey.mascota.infraestructure.MascotaRepository;
import com.dbp.pet_journey.servicio.domain.EstadoServicio;
import com.dbp.pet_journey.servicio.domain.Servicio;
import com.dbp.pet_journey.servicio.dto.ServicioRequestDto;
import com.dbp.pet_journey.servicio.dto.ServicioResponseDto;
import com.dbp.pet_journey.servicio.infraestructure.ServicioRepository;
import com.dbp.pet_journey.usuario.dto.UsuarioRequestDto;
import com.dbp.pet_journey.usuario.dto.UsuarioResponseDto;
import com.dbp.pet_journey.usuario.dto.UsuarioUpdateRequestDto;
import com.dbp.pet_journey.usuario.infraestructure.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MascotaService mascotaService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private EmailService emailService;

    @Value("${MAIL_USERNAME:yitzhak.namihas@utec.edu.pe}")
    String MAIL_USERNAME;

   public ResponseEntity<UsuarioResponseDto> loginUsuario(UsuarioRequestDto usuarioRequestDto) {
        if (usuarioRepository.existsByUsername(usuarioRequestDto.getUsername())) {
            throw new ResourceConflictException("El nombre de usuario ya esta en uso");
        }

        if (usuarioRepository.existsByEmail(usuarioRequestDto.getEmail())) {
            throw new ResourceConflictException("El correo electronico ya esta registrado");
        }

        Usuario usuario = new Usuario();
        modelMapper.map(usuarioRequestDto, usuario);
        usuarioRepository.save(usuario);
        UsuarioResponseDto usuarioResponseDto = modelMapper.map(usuario, UsuarioResponseDto.class);
        return ResponseEntity.ok(usuarioResponseDto);
    }
  
    public UsuarioResponseDto getUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioResponseDto.class);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }


    public ResponseEntity<UsuarioResponseDto> updateUsuario(Long id, UsuarioUpdateRequestDto usuarioUpdateRequestDto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (!usuario.getUsername().equals(usuarioUpdateRequestDto.getUsername()) &&
                usuarioRepository.existsByUsername(usuarioUpdateRequestDto.getUsername())) {
            throw new ResourceConflictException("El nombre de usuario ya está en uso");
        }

        if (!usuario.getEmail().equals(usuarioUpdateRequestDto.getEmail()) &&
                usuarioRepository.existsByEmail(usuarioUpdateRequestDto.getEmail())) {
            throw new ResourceConflictException("El correo electronico ya esta registrado");
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(usuarioUpdateRequestDto, usuario);

        usuarioRepository.save(usuario);

        UsuarioResponseDto usuarioResponseDto = modelMapper.map(usuario, UsuarioResponseDto.class);

        return ResponseEntity.ok(usuarioResponseDto);
    }

    public Usuario agregarMascota(Long id, MascotaRequestDto mascotaRequestDto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        Mascota mascota= mascotaService.saveMascota(mascotaRequestDto,usuario);
        usuario.getMascotas().add(mascota);
        usuarioRepository.save(usuario);
        return usuario;
    }


    public Usuario eliminarMascota(Long usuarioId, Long mascotaId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));

        usuario.getMascotas().remove(mascota);

        mascotaRepository.delete(mascota);

        return usuarioRepository.save(usuario);
    }

    public ResponseEntity<MascotaUpdateResponseDto> actualizarMascota( Long mascotaId, MascotaUpdateRequestDto mascotaUpdateRequestDto){
        return  mascotaService.updateMascota(mascotaId,mascotaUpdateRequestDto);
    }

    public ServicioResponseDto setMascotaServicio(Long mascotaId, Long servicioId) {
        Servicio servicio = servicioRepository.findById(servicioId).orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado"));
        Mascota mascota = mascotaRepository.findById(mascotaId).orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));
        Usuario usuario = mascota.getUsuario();
        servicio.getMascotas().add(mascota);
        Cuidador cuidador = mascota.getCuidador();
        mascota.setCuidador(servicio.getCuidador());

        Map<String, Object> props = Map.of(
                "nombreUsuario", usuario.getName(),
                "nombreMascota", mascota.getName(),
                "nombreCuidador", cuidador.getName(),
                "emailCuidador", cuidador.getEmail(),
                "telefonoCuidador", cuidador.getPhoneNumber(),
                "experienciaCuidador", cuidador.getExperience()
        );
        // Crear el correo usando la plantilla Thymeleaf
        Mail mail = Mail.builder()
                .to(usuario.getEmail())           // Correo del usuario
                .from(MAIL_USERNAME)   // Cambia esto por tu correo o configuración
                .subject("Detalles del Cuidador Asignado")
                .htmlTemplate(new Mail.HtmlTemplate("cuidador-info", props)) // La plantilla Thymeleaf
                .build();

        // Enviar el correo
        try {
            emailService.sendEmail(mail);
        } catch (Exception e) {
            // Manejo de errores al enviar el correo
            e.printStackTrace();
        }

        servicio.setEstado(EstadoServicio.PENDIENTE);
        servicioRepository.save(servicio);
        ServicioResponseDto servicioResponseDto = new ServicioResponseDto();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(servicio, ServicioResponseDto.class);
        return servicioResponseDto;
    }


}
