package org.example.retropvpadmin.dao.impl;

import org.example.retropvpadmin.config.ConexionBBDD;
import org.example.retropvpadmin.config.SchemDB;
import org.example.retropvpadmin.dao.interfaces.ISalaDao;
import org.example.retropvpadmin.model.Sala;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class SalaDaoImpl implements ISalaDao {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public SalaDaoImpl() {
        connection = ConexionBBDD.getConnection();
    }

    @Override
    public List<Sala> listarSalas() {
        List<Sala> salas = new ArrayList<>();
        String query = String.format("SELECT %s, %s, %s FROM %s",
                SchemDB.ID_SALA, SchemDB.SL_NOMBRE, SchemDB.SL_TAMANIO, SchemDB.TAB_SALA);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                salas.add(new Sala(
                        resultSet.getInt(SchemDB.ID_SALA),
                        resultSet.getString(SchemDB.SL_NOMBRE),
                        resultSet.getInt(SchemDB.SL_TAMANIO)
                ));
            }
        } catch (SQLException e) {
            out.println("Error listarSalas: " + e.getMessage());
            e.printStackTrace();
        }
        return salas;
    }
}