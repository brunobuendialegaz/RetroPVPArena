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

    public static TorneoEstadoEnum fromValorDB(String valor) {
        for (TorneoEstadoEnum e : values()) {
            if (e.getValorDB().equals(valor)) return e;
        }
        return CREADO;
    }
}

// enum('creado','iniciado','terminado')
