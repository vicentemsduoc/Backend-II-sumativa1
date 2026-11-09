package com.petsalud.backendpetsalud.dto;

import lombok.Data;

@Data
public class MascotaResponseDTO {
    private Long id;
    private String nombre;
    private String especie;
    private String raza;
    private String sexo;
    private String fechaNacimiento;
    private Long propietarioId;
}