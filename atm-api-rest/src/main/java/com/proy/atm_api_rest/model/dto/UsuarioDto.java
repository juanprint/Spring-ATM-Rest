package com.proy.atm_api_rest.model.dto;

import jakarta.persistence.*;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDto implements Serializable {
    private Integer usuarioId;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String passwordHash;
}
