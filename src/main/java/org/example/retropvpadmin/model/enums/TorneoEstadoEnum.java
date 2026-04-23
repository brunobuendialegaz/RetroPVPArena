package org.example.retropvpadmin.model.enums;

import lombok.Getter;

@Getter
public enum TorneoEstadoEnum {
    CREADO("creado"),
    INICIADO("iniciado"),
    TERMINADO("terminado");

    private final String valorDB;

    TorneoEstadoEnum(String valorDB){
        this.valorDB = valorDB;
    }
}

// enum('creado','iniciado','terminado')
