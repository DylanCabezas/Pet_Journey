package com.dbp.pet_journey.mascota.domain;

import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaResponseDto;
import com.dbp.pet_journey.mascota.dto.MascotaUpdateRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaUpdateResponseDto;
import com.dbp.pet_journey.mascota.infraestructure.MascotaRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    public void saveMascota(MascotaRequestDto mascotaRequestDto) {
        Mascota mascota = new Mascota();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(mascotaRequestDto, mascota);
        mascotaRepository.save(mascota);
        MascotaResponseDto mascotaResponseDto = modelMapper.map(mascota, MascotaResponseDto.class);
        ResponseEntity.ok(mascotaResponseDto);
    }

    public MascotaResponseDto getMascota(Long id) {
        Mascota mascota = mascotaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mascota, MascotaResponseDto.class);
    }

    public ResponseEntity<MascotaUpdateResponseDto> updateMascota(Long id, MascotaUpdateRequestDto mascotaUpdateRequestDto) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(mascotaUpdateRequestDto, mascota);

        mascotaRepository.save(mascota);

        MascotaUpdateResponseDto mascotaUpdateResponseDto = modelMapper.map(mascota, MascotaUpdateResponseDto.class);

        return ResponseEntity.ok(mascotaUpdateResponseDto);
    }



    public void deleteMascota(Long id) {
        mascotaRepository.deleteById(id);
    }


    @Scheduled(cron = "0 0 0 * * *")
    public void verificarBirthdayMascotas() {
        LocalDate fechaActual = LocalDate.now();
        int diaActual = fechaActual.getDayOfMonth();
        int mesActual = fechaActual.getMonthValue();

        List<Mascota> mascotasQueCumplenBirthday = mascotaRepository.findMascotasByNuevaEdad(diaActual, mesActual);

        for (Mascota mascota : mascotasQueCumplenBirthday) {
            mascota.setAge(mascota.getAge() + 1);

            String mensajeBirthday = "Happy Birthday, " + mascota.getName() + "!";
            logger.info(mensajeBirthday);
        }


        mascotaRepository.saveAll(mascotasQueCumplenBirthday);
    }

}
