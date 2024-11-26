package com.dbp.pet_journey.usuario.domain;

import com.dbp.pet_journey.Exceptions.ResourceConflictException;
import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import com.dbp.pet_journey.auth.domain.Role;
import com.dbp.pet_journey.auth.dto.JwtAuthResponse;
import com.dbp.pet_journey.auth.dto.LoginReq;
import com.dbp.pet_journey.config.JwtService;
import com.dbp.pet_journey.cuidador.domain.Cuidador;
import com.dbp.pet_journey.mail.domain.EmailService;
import com.dbp.pet_journey.mail.model.Mail;
import com.dbp.pet_journey.mascota.domain.Mascota;
import com.dbp.pet_journey.mascota.domain.MascotaService;
import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaResponseDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
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
    @Autowired
    JwtService jwtService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;


    @Value("${MAIL_USERNAME:yitzhak.namihas@utec.edu.pe}")
    String MAIL_USERNAME;


   public JwtAuthResponse  register(UsuarioRequestDto usuarioRequestDto) {
        if (usuarioRepository.existsByUsername(usuarioRequestDto.getUsername())) {
            throw new ResourceConflictException("El nombre de usuario ya esta en uso");
        }

        if (usuarioRepository.existsByEmail(usuarioRequestDto.getEmail())) {
            throw new ResourceConflictException("El correo electronico ya esta registrado");
        }

        System.out.println("Register");

        Usuario usuario = new Usuario();
        modelMapper.map(usuarioRequestDto, usuario);
        usuario.setRole(Role.CLIENTE);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        var jwt = jwtService.generateToken(usuario);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(jwt);
        return response;

    }

    public JwtAuthResponse login(LoginReq request) throws IllegalArgumentException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = usuarioRepository.findByEmail(request.getEmail());
        var jwt = jwtService.generateToken(user);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(jwt);

        return response;
    }
  
    public UsuarioResponseDto getUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        ModelMapper modelMapper = new ModelMapper();
        UsuarioResponseDto usuarioResponseDto = modelMapper.map(usuario, UsuarioResponseDto.class);
        return usuarioResponseDto;
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

    public Page<Mascota> agregarMascota(Long id, MascotaRequestDto mascotaRequestDto, Pageable pageable) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        Mascota mascota = mascotaService.saveMascota(mascotaRequestDto, usuario);
        usuario.getMascotas().add(mascota);
        usuarioRepository.save(usuario);

        // Obtener todas las mascotas del usuario
        List<Mascota> todasLasMascotas = usuario.getMascotas();

        // Calcular el índice de inicio y fin para la página actual
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), todasLasMascotas.size());

        // Crear una sublist para la página actual
        List<Mascota> mascotasPaginadas = todasLasMascotas.subList(start, end);

        // Crear y retornar un objeto Page
        return new PageImpl<>(mascotasPaginadas, pageable, todasLasMascotas.size());
    }


    public Page<Mascota> eliminarMascota(Long usuarioId, Long mascotaId, Pageable pageable) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));

        usuario.getMascotas().remove(mascota);
        mascotaRepository.delete(mascota);
        usuario = usuarioRepository.save(usuario);

        // Obtener todas las mascotas del usuario
        List<Mascota> todasLasMascotas = usuario.getMascotas();

        // Calcular el índice de inicio y fin para la página actual
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), todasLasMascotas.size());

        // Crear una sublist para la página actual
        List<Mascota> mascotasPaginadas = todasLasMascotas.subList(start, end);

        // Crear y retornar un objeto Page
        return new PageImpl<>(mascotasPaginadas, pageable, todasLasMascotas.size());
    }

    public Page<MascotaResponseDto> actualizarMascota(Long mascotaId, MascotaUpdateRequestDto mascotaUpdateRequestDto, int page, int size) {
        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(mascotaUpdateRequestDto, mascota);

        mascotaRepository.save(mascota);

        Pageable pageable = PageRequest.of(page, size);
        Page<Mascota> mascotasPage = mascotaRepository.findAll(pageable);

        return mascotasPage.map(m -> modelMapper.map(m, MascotaResponseDto.class));
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
    public MascotaResponseDto getMascota(Long usuarioId, Long mascotaId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));

        if (!usuario.getMascotas().contains(mascota)) {
            throw new ResourceNotFoundException("La mascota no pertenece a este usuario");
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mascota, MascotaResponseDto.class);
    }

    public Page<MascotaResponseDto> getMascotas(Long usuarioId, Pageable pageable) {
        Page<Mascota> mascotas = mascotaRepository.findByUsuarioId(usuarioId, pageable);
        return mascotas.map(mascota -> modelMapper.map(mascota, MascotaResponseDto.class));
    }


}
