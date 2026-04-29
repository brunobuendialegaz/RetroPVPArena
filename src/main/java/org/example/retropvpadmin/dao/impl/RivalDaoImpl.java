package org.example.retropvpadmin.dao.impl;

import org.example.retropvpadmin.config.ConexionBBDD;
import org.example.retropvpadmin.config.SchemDB;
import org.example.retropvpadmin.dao.interfaces.IRivalDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RivalDaoImpl implements IRivalDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public RivalDaoImpl() {
        connection = ConexionBBDD.getConnection();
    }

    @Override
    public boolean asignarRival(int idUsuario, int idTorneo, int idEnfrentamiento) {
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s) " +
                "VALUES (?, ?, ?, FALSE)",
                SchemDB.TAB_RIVAL, SchemDB.ID_USUARIO, SchemDB.ID_TORNEO, SchemDB.ID_ENFRENTAMIENTO, SchemDB.R_GANADOR);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setInt(2, idTorneo);
            preparedStatement.setInt(3, idEnfrentamiento);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error asignarRival: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean marcarGanador(int idUsuario, int idTorneo, int idEnfrentamiento) {
        String query = String.format("UPDATE %s SET %s = TRUE " +
                "WHERE %s = ? AND %s = ? AND %s = ?",
                SchemDB.TAB_RIVAL, SchemDB.R_GANADOR, SchemDB.ID_USUARIO, SchemDB.ID_TORNEO, SchemDB.ID_ENFRENTAMIENTO);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setInt(2, idTorneo);
            preparedStatement.setInt(3, idEnfrentamiento);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error marcarGanador: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
