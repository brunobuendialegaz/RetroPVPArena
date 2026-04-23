package org.example.retropvpadmin.model.enums;

import lombok.Getter;

@Getter
public enum TipoUsuarioEnum {
    ADMINISTRADOR("Administrador"),
    JUGADOR("Jugador");

    private final String valorDB;

    TipoUsuarioEnum(String valorDB){
        this.valorDB = valorDB;
    }

}
// enum('Administrador','Jugador')