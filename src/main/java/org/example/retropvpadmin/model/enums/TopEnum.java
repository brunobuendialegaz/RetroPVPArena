package org.example.retropvpadmin.model.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum TopEnum {


    FINAL("final"),
    SEMIS("semifinal"),
    CUARTOS("cuartos"),
    OCTAVOS("octavos"),
    DIECISEISAVOS("dieciseisavos"),
    PREVIA("previa");


        private final String valorDB;

    TopEnum(String valorDB){
            this.valorDB = valorDB;
        }

    public static TopEnum fromValorDB(String valor) {
        for (TopEnum top : values()) {
            if (top.getValorDB().equals(valor)) return top;
        }
        return PREVIA; // default
    }


        // 'final', 'semifinal', 'cuartos', 'octavos', 'dieciseisavos', 'previa'

}
