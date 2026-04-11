package com.proy.atm_api_rest.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, etc.
@NoArgsConstructor // Requerido por JPA para crear el objeto vacío
@AllArgsConstructor // Requerido por @Builder para funcionar
@Entity
@Builder //DesignPatter: Permite crear usuarios con el estilo .builder()
@Table(name = "cuentas")
public class CuentaEntity {
    @Id
    @Column(name="cuenta_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cuentaId;

    @Column(name="nombre",nullable = false,length = 20)
    private String nombreCuenta;

    @ManyToOne// Muchos registros de cuenta para UN usuario
    @Column(name="usuario_id",nullable = false)
    private Integer usuarioId;

    @Column(name="tipo_cuenta",nullable = false)
    private TipoCuenta tipoCuenta;

    @Column(name="saldo",precision = 15,scale = 2,nullable = false)
    private Double saldo;

    @Column(name="numero_cuenta",nullable = false,length = 20)
    private String numeroCuenta;
}
