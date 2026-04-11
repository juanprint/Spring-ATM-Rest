package com.proy.atm_api_rest.model.payload;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder

public class MensajeResponse implements Serializable {
    private String mensaje;
    private Object object;
}
