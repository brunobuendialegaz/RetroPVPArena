package org.example.retropvpadmin.service;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lombok.Getter;
import lombok.Setter;
import org.example.retropvpadmin.dao.impl.UsuarioDaoImpl;
import org.example.retropvpadmin.dao.interfaces.IUsuarioDao;
import org.example.retropvpadmin.model.Usuario;
import org.example.retropvpadmin.util.ControlSesion;
import org.example.retropvpadmin.util.LanzadorAlertas;


@Getter
@Setter
public class AuthService {

    private IUsuarioDao dao;

    private Usuario user;

    private Navegacion nav;

    private LanzadorAlertas alert;

    public AuthService(){
        dao = new UsuarioDaoImpl();
        nav = new Navegacion();
        alert = new LanzadorAlertas();
    }

    public void login(ActionEvent event, String email, String pass){
        user = dao.checkLogin(email,pass);
        if (user!=null){
            ControlSesion.getInstance().setUsuarioActivo(user);
            nav.irAPanel(event);
        } else {
            alert.lanzarAlerta(1, "Datos de acceso no validos.");
        }
    }

}
