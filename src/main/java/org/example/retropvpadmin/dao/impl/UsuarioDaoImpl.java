package org.example.retropvpadmin.dao.impl;

import org.example.retropvpadmin.config.ConexionBBDD;
import org.example.retropvpadmin.config.SchemDB;
import org.example.retropvpadmin.dao.interfaces.IUsuarioDao;
import org.example.retropvpadmin.model.Usuario;
import org.example.retropvpadmin.util.ControlSesion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDaoImpl implements IUsuarioDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UsuarioDaoImpl(){
        connection = ConexionBBDD.getConnection();
    }

    @Override
    public Usuario checkLogin(String mail, String pass) {
        String query = String.format("select * from %s where %s=? and %s=?",
                SchemDB.TAB_USUARIO, SchemDB.U_EMAIL, SchemDB.U_DNI);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                long id = resultSet.getInt(SchemDB.ID_USUARIO);
                long idTipo = resultSet.getInt(SchemDB.ID_TIPO_USUARIO);
                String nombre = resultSet.getString(SchemDB.U_NOMBRE);
                String apellido = resultSet.getString(SchemDB.U_APELLIDO);
                String email = resultSet.getString(SchemDB.U_EMAIL);
                String direccion = resultSet.getString(SchemDB.U_DIRECCION);
                String telefono = resultSet.getString(SchemDB.U_TLF);
                String DNI = resultSet.getString(SchemDB.U_DNI);
                Usuario usuario = new Usuario(id, idTipo, nombre, apellido, email, direccion, telefono, DNI);
                ControlSesion.getInstance().setUsuarioActivo(usuario);
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public long totalUsuarios() {
        String query = String.format("select count(*) as total from %s;", SchemDB.TAB_USUARIO);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return List.of();
    }

    @Override
    public boolean checkDNI() {
        return false;
    }
}
