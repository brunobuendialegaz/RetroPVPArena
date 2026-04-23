package org.example.retropvpadmin.model.enums;

import lombok.Getter;

@Getter
public enum PedidoEstadoEnum {

    CREADO("creado"),
    PAGADO("pagado"),
    PREPARADO("preparado"),
    ENVIADO("enviado"),
    ENTREGADO("entregado"),
    INCIDENCIA("incidencia"),
    ELIMINADO("eliminado");

    private final String valorDB;

    PedidoEstadoEnum(String valorDB){
        this.valorDB = valorDB;
    }


    // enum('creado','pagado','preparado','enviado','entregado','incidencia','eliminado')
}
