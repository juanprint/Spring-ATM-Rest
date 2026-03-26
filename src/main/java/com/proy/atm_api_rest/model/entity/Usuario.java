package com.proy.atm_api_rest.model.entity;

import jakarta.persistence.*;
import lombok.ToString;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios")

public class Usuario implements Serializable {
    @Id
    @Column(name="usuario_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer usuarioId;

    @Column(name="usuario_id")
    private String nombre;

    @Column(name="nombre")
    private String apellido;

    @Column(name="dni")
    private String dni;

    @Column(name="email")
    private String email;

    @Column(name="password_Hash")
    private String passwordHash;
}
