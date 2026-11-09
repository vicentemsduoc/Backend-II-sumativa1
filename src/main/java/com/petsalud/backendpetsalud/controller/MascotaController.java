package com.petsalud.backendpetsalud.controller;

import com.petsalud.backendpetsalud.dto.MascotaResponseDTO;
import com.petsalud.backendpetsalud.entity.Mascota;
import com.petsalud.backendpetsalud.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @PostMapping("/propietario/{propietarioId}")
    public MascotaResponseDTO registrarMascota(@PathVariable Long propietarioId, @RequestBody Mascota mascota) {
        Mascota mascotaGuardada = mascotaService.registrarMascota(mascota, propietarioId);

        MascotaResponseDTO dto = new MascotaResponseDTO();
        dto.setId(mascotaGuardada.getId());
        dto.setNombre(mascotaGuardada.getNombre());
        dto.setEspecie(mascotaGuardada.getEspecie());
        dto.setRaza(mascotaGuardada.getRaza());
        dto.setSexo(mascotaGuardada.getSexo());
        dto.setFechaNacimiento(mascotaGuardada.getFechaNacimiento() != null
                ? mascotaGuardada.getFechaNacimiento().toString()
                : null);
        dto.setPropietarioId(mascotaGuardada.getPropietario() != null
                ? mascotaGuardada.getPropietario().getId()
                : null);

        return dto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerMascotaPorId(@PathVariable Long id) {
        return mascotaService.obtenerMascotaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaResponseDTO> actualizarMascota(
            @PathVariable Long id,
            @RequestBody Mascota mascotaActualizada) {

        Mascota mascota = mascotaService.actualizarMascota(id, mascotaActualizada);

        MascotaResponseDTO dto = new MascotaResponseDTO();
        dto.setId(mascota.getId());
        dto.setNombre(mascota.getNombre());
        dto.setEspecie(mascota.getEspecie());
        dto.setRaza(mascota.getRaza());
        dto.setSexo(mascota.getSexo());
        dto.setFechaNacimiento(mascota.getFechaNacimiento() != null
                ? mascota.getFechaNacimiento().toString()
                : null);
        dto.setPropietarioId(mascota.getPropietario() != null
                ? mascota.getPropietario().getId()
                : null);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/propietario/{propietarioId}")
    public ResponseEntity<List<Mascota>> listarMascotasPorPropietario(@PathVariable Long propietarioId) {
        List<Mascota> mascotas = mascotaService.listarMascotasPorPropietario(propietarioId);
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping
    public List<MascotaResponseDTO> listarMascotas() {
        return mascotaService.listarMascotas().stream()
                .map(mascota -> {
                    MascotaResponseDTO dto = new MascotaResponseDTO();
                    dto.setId(mascota.getId());
                    dto.setNombre(mascota.getNombre());
                    dto.setEspecie(mascota.getEspecie());
                    dto.setRaza(mascota.getRaza());
                    dto.setSexo(mascota.getSexo());
                    dto.setFechaNacimiento(mascota.getFechaNacimiento() != null
                            ? mascota.getFechaNacimiento().toString()
                            : null);
                    dto.setPropietarioId(mascota.getPropietario() != null
                            ? mascota.getPropietario().getId()
                            : null);
                    return dto;
                })
                .toList();
    }
}
