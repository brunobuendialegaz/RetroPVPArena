package org.example.retropvpadmin.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.retropvpadmin.model.Usuario;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ControlSesion {

    private static ControlSesion instancia;

    private Usuario usuarioActivo;

    public static ControlSesion getInstance(){
        if (instancia == null){
            instancia = new ControlSesion();
        }
        return instancia;
    }



}
