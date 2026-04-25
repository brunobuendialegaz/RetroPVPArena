package org.example.retropvpadmin.service;

import lombok.Getter;
import lombok.Setter;
import org.example.retropvpadmin.dao.impl.UsuarioDaoImpl;
import org.example.retropvpadmin.dao.interfaces.IUsuarioDao;

@Getter
@Setter
public class AuthService {

    private IUsuarioDao dao;

    public AuthService(){
        dao = new UsuarioDaoImpl();
    }

}
