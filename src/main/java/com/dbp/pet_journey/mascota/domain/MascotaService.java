package com.dbp.pet_journey.mascota.domain;

import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaResponseDto;
import com.dbp.pet_journey.mascota.dto.MascotaUpdateRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaUpdateResponseDto;
import com.dbp.pet_journey.mascota.infraestructure.MascotaRepository;
import com.dbp.pet_journey.usuario.domain.Usuario;
import com.dbp.pet_journey.usuario.domain.UsuarioService;
import com.dbp.pet_journey.usuario.infraestructure.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    private static final Logger logger = LoggerFactory.getLogger(MascotaService.class);


    public Mascota saveMascota(MascotaRequestDto mascotaRequestDto, Usuario usuario) {
        Mascota mascota = new Mascota();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(mascotaRequestDto, mascota);
        mascota.setUsuario(usuario);
        mascotaRepository.save(mascota);
        return mascota;
    }

    public MascotaResponseDto getMascota(Long id) {
        Mascota mascota = mascotaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mascota, MascotaResponseDto.class);
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


    public void deleteMascota(Long id) {
        mascotaRepository.deleteById(id);
    }


    @Scheduled(cron = "0 0 0 * * *")
    public void verificarBirthdayMascotas() {
        LocalDate fechaActual = LocalDate.now();

        List<Mascota> mascotasQueCumplenBirthday = mascotaRepository.findMascotasByFechaNacimiento(fechaActual);

        for (Mascota mascota : mascotasQueCumplenBirthday) {
            mascota.setAge(mascota.getAge() + 1);

            String mensajeBirthday = "Happy Birthday, " + mascota.getName() + "!";
            logger.info(mensajeBirthday);
        }


        mascotaRepository.saveAll(mascotasQueCumplenBirthday);
    }

}