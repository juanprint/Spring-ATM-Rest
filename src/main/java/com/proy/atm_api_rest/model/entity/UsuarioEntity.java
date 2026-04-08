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
    @Column(name="usuario_id",nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer usuarioId;

    @Column(name="nombre",nullable = false)
    private String nombre;

    @Column(name="apellido",nullable = false)
    private String apellido;

    @Column(name="dni",nullable = false)
    private String dni;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name="password_Hash",nullable = false)
    private String passwordHash;
}
