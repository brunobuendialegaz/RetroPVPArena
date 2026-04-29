package org.example.retropvpadmin.dao.interfaces;

import org.example.retropvpadmin.model.Usuario;

import java.util.List;

public interface IUsuarioDao {

    Usuario checkLogin(String mail, String pass);

    long totalUsuarios();

    List<Usuario> listarUsuarios();

    int checkDNI(String dni);

    boolean crearUsuario(long tipoUsuario, String nombre, String apellido, String email, String direccion, String telefono, String DNI);

    boolean actualizarUsuario(long tipoUsuario, String nombre, String apellido, String email, String direccion, String telefono, String DNI);

    List<Usuario> listarJugadores();
}
