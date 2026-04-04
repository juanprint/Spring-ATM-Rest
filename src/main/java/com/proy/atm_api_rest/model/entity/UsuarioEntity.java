package com.proy.atm_api_rest.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="usuarios")

public class UsuarioEntity implements Serializable {
    @Id
    @Column(name="usuario_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer usuarioId;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @Column(name="dni")
    private String dni;

    @Column(name="email")
    private String email;

    @Column(name="password_Hash")
    private String passwordHash;
}
