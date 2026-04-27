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
import java.util.ArrayList;
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
        List <Usuario> usuarios = new ArrayList<>();
        String query = String.format("select * from %s;", SchemDB.TAB_USUARIO);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                long idUsuario = resultSet.getLong(SchemDB.ID_USUARIO);
                long tipoUsuario = resultSet.getLong(SchemDB.ID_TIPO_USUARIO);
                String nombre = resultSet.getString(SchemDB.U_NOMBRE);
                String apellido = resultSet.getString(SchemDB.U_APELLIDO);
                String email = resultSet.getString(SchemDB.U_EMAIL);
                String direccion = resultSet.getString(SchemDB.U_DIRECCION);
                String telefono = resultSet.getString(SchemDB.U_TLF);
                String DNI = resultSet.getString(SchemDB.U_DNI);
                usuarios.add(new Usuario(idUsuario,tipoUsuario,nombre,apellido,email,direccion,telefono,DNI));
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            System.out.println(e.getMessage());
        }
        return usuarios;
    }

    @Override
    public int checkDNI(String dni) { // return 1 existe, return 0 no existe
        String query = String.format("select count(*) as total from %s where %s = ?;",
                SchemDB.TAB_USUARIO,
                SchemDB.U_DNI);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dni);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public boolean crearUsuario(long tipoUsuario, String nombre, String apellido, String email, String direccion, String telefono, String DNI){
        String query = String.format("insert into %s (%s,%s,%s,%s,%s,%s,%s) values (?,?,?,?,?,?,?);",
                SchemDB.TAB_USUARIO, SchemDB.ID_TIPO_USUARIO, SchemDB.U_NOMBRE, SchemDB.U_APELLIDO, SchemDB.U_EMAIL,
                SchemDB.U_DIRECCION, SchemDB.U_TLF, SchemDB.U_DNI);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, tipoUsuario);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellido);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, direccion);
            preparedStatement.setString(6, telefono);
            preparedStatement.setString(7, DNI);
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            System.out.println(e.getMessage());
        }
        return false;
    };

    @Override
    public boolean actualizarUsuario(long tipoUsuario, String nombre, String apellido, String email, String direccion, String telefono, String DNI){
        String query = String.format("UPDATE %s \n" +
                        "SET %s = ?, \n" +
                        "    %s = ?, \n" +
                        "    %s = ?, \n" +
                        "    %s = ?, \n" +
                        "    %s = ?, \n" +
                        "    %s = ? \n" +
                        "WHERE %s = ?;",
                SchemDB.TAB_USUARIO, SchemDB.ID_TIPO_USUARIO, SchemDB.U_NOMBRE, SchemDB.U_APELLIDO, SchemDB.U_EMAIL,
                SchemDB.U_DIRECCION, SchemDB.U_TLF, SchemDB.U_DNI);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, tipoUsuario);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellido);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, direccion);
            preparedStatement.setString(6, telefono);
            preparedStatement.setString(7, DNI);
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            System.out.println(e.getMessage());
        }
        return false;
    };
}
