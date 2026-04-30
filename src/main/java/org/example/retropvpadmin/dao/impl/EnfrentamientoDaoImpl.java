package org.example.retropvpadmin.dao.impl;

import org.example.retropvpadmin.config.ConexionBBDD;
import org.example.retropvpadmin.config.SchemDB;
import org.example.retropvpadmin.dao.interfaces.IEnfrentamientoDao;
import org.example.retropvpadmin.model.*;
import org.example.retropvpadmin.model.enums.TopEnum;

import java.sql.*;
import java.util.*;

public class EnfrentamientoDaoImpl implements IEnfrentamientoDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public EnfrentamientoDaoImpl() {
        connection = ConexionBBDD.getConnection();
    }

    @Override
    public List<Enfrentamiento> listadoEnfrentamientos(int idTorneo) {
        List<Enfrentamiento> enfrentamientos = new ArrayList<>();
        // Traemos enfrentamiento + sus rivales en una sola query
        String query = String.format("SELECT e.%s, e.%s, e.%s, e.%s, " +
                "r.%s, r.%s, u.%s AS u_nombre, u.%s " +
                "FROM %s e " +
                "JOIN %s r ON r.%s = e.%s " +
                "JOIN %s u ON u.%s = r.%s " +
                "WHERE r.%s = ? " +
                "ORDER BY e.%s",
                SchemDB.ID_ENFRENTAMIENTO, SchemDB.E_NOMBRE, SchemDB.E_TOP, SchemDB.E_PARTICIPANTES,
                SchemDB.ID_USUARIO, SchemDB.R_GANADOR, SchemDB.U_NOMBRE, SchemDB.U_APELLIDO,
                SchemDB.TAB_ENFRENTAMIENTO,
                SchemDB.TAB_RIVAL, SchemDB.ID_ENFRENTAMIENTO, SchemDB.ID_ENFRENTAMIENTO,
                SchemDB.TAB_USUARIO, SchemDB.ID_USUARIO, SchemDB.ID_USUARIO,
                SchemDB.ID_TORNEO,
                SchemDB.ID_ENFRENTAMIENTO);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idTorneo);
            resultSet = preparedStatement.executeQuery();
            Map<Integer, Enfrentamiento> mapa = new LinkedHashMap<>();
            while (resultSet.next()) {
                int idEnf = resultSet.getInt(SchemDB.ID_ENFRENTAMIENTO);
                Enfrentamiento enf = mapa.computeIfAbsent(idEnf, k -> {
                    Enfrentamiento e = new Enfrentamiento();
                    try {
                        e.setIdEnfrentamiento(resultSet.getInt(SchemDB.ID_ENFRENTAMIENTO));
                        e.setNombre(resultSet.getString(SchemDB.E_NOMBRE));
                        e.setTop(TopEnum.fromValorDB(resultSet.getString(SchemDB.E_TOP)));
                        e.setParticipantes(resultSet.getInt(SchemDB.E_PARTICIPANTES));
                        e.setRivals(new HashSet<>());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    return e;
                });
                Usuario u = new Usuario();
                u.setIdUsuario(resultSet.getLong("id_usuario"));
                u.setNombre(resultSet.getString("u_nombre"));
                u.setApellido(resultSet.getString(SchemDB.U_APELLIDO));// todo Constructor
                ParticipacionId pId = new ParticipacionId(idTorneo, (int) u.getIdUsuario());
                Participacion p = new Participacion();// todo Constructor
                p.setId(pId);
                p.setUsuario(u);
                RivalId rId = new RivalId(idEnf, idTorneo, (int) u.getIdUsuario());
                Rival rival = new Rival();// todo Constructor
                rival.setId(rId);
                rival.setParticipacion(p);
                rival.setEnfrentamiento(enf);
                rival.setEsGanador(resultSet.getBoolean(SchemDB.R_GANADOR));

                enf.getRivals().add(rival);
            }
            enfrentamientos.addAll(mapa.values());
        } catch (SQLException e) {
            System.out.println("Error listadoEnfrentamientos: " + e.getMessage());
            e.printStackTrace();
        }
        return enfrentamientos;
    }

    @Override
    public int crearEnfrentamiento(String nombre, String top, int participantes) {
        String query = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)",
                SchemDB.TAB_ENFRENTAMIENTO, SchemDB.E_NOMBRE, SchemDB.E_TOP, SchemDB.E_PARTICIPANTES);
        try {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, top);
            preparedStatement.setInt(3, participantes);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) return resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println("Error crearEnfrentamiento: " + e.getMessage());
            e.printStackTrace();
        }
        return -1; // error
    }
}
