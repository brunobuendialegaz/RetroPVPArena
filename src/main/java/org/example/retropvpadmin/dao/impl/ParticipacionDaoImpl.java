package org.example.retropvpadmin.dao.impl;

import org.example.retropvpadmin.config.ConexionBBDD;
import org.example.retropvpadmin.config.SchemDB;
import org.example.retropvpadmin.dao.interfaces.IParticipacionDao;
import org.example.retropvpadmin.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipacionDaoImpl implements IParticipacionDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ParticipacionDaoImpl() {
        connection = ConexionBBDD.getConnection();
    }

    @Override
    public List<Usuario> listadoParticipantes(int idTorneo) {
        List<Usuario> participantes = new ArrayList<>();
        String query = String.format("SELECT u.%s, u.%s, u.%s " +
                "FROM %s p " +
                "JOIN %s u ON u.%s = p.%s " +
                "WHERE p.%s = ?",
                SchemDB.ID_USUARIO, SchemDB.U_NOMBRE, SchemDB.U_APELLIDO,
                SchemDB.TAB_PARTICIPACION,
                SchemDB.TAB_USUARIO, SchemDB.ID_USUARIO, SchemDB.ID_USUARIO,
                SchemDB.ID_TORNEO);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idTorneo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Usuario usuario = new Usuario(resultSet.getLong(SchemDB.ID_USUARIO),
                        resultSet.getString(SchemDB.U_NOMBRE),
                        resultSet.getString(SchemDB.U_APELLIDO));
                participantes.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error listadoParticipantes: " + e.getMessage());
            e.printStackTrace();
        }
        return participantes;
    }

    @Override
    public boolean anadirParticipante(int idUsuario, int idTorneo) {
        String query = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                SchemDB.TAB_PARTICIPACION, SchemDB.ID_USUARIO, SchemDB.ID_TORNEO);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setInt(2, idTorneo);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error añadirParticipante: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarParticipante(int idUsuario, int idTorneo) {
        String query = String.format("DELETE FROM %s WHERE %s = ? AND %s = ?",
                SchemDB.TAB_PARTICIPACION, SchemDB.ID_USUARIO, SchemDB.ID_TORNEO);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setInt(2, idTorneo);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error eliminarParticipante: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
