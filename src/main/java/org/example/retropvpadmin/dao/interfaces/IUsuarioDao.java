package org.example.retropvpadmin.dao.interfaces;

import org.example.retropvpadmin.model.Usuario;

import java.util.List;

public interface IUsuarioDao {

    Usuario checkLogin(String mail, String pass);

    long totalUsuarios();

    List<Usuario> listarUsuarios();

    boolean checkDNI();

}
