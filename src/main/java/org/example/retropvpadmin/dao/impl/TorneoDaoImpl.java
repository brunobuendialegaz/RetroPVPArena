package org.example.retropvpadmin.dao.impl;

import org.example.retropvpadmin.config.ConexionBBDD;
import org.example.retropvpadmin.config.SchemDB;
import org.example.retropvpadmin.dao.interfaces.ITorneoDao;
import org.example.retropvpadmin.model.*;
import org.example.retropvpadmin.model.enums.TorneoEstadoEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TorneoDaoImpl implements ITorneoDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public TorneoDaoImpl(){
        connection = ConexionBBDD.getConnection();
    }
    @Override
    public long torneosProgramados() {
        String query = String.format("select count(distinct t.%s ) as torneos_pendientes\n" +
                        "    from %s t \n" +
                        "    where t.%s <> 'terminado';"
                , SchemDB.ID_TORNEO,
                SchemDB.TAB_TORNEO,
                SchemDB.T_ESTADO);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("torneos_pendientes");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Torneo> listadoTorneos() {
        List<Torneo> torneos = new ArrayList<>();
        String query = String.format("SELECT t.%s, t.%s, t.%s, t.%s, " +
                        "t.%s, a.%s AS juego_nombre, " +
                        "t.%s, s.%s AS sala_nombre, s.%s AS sala_tamanio, " +
                        "t.%s, u.%s AS arb_nombre, u.%s AS arb_apellido " +
                        "FROM %s t " +
                        "LEFT JOIN %s j ON j.%s = t.%s " +   // ← j.id_articulo = t.id_juego
                        "LEFT JOIN %s a ON a.%s = j.%s " +
                        "LEFT JOIN %s s ON s.%s = t.%s " +
                        "LEFT JOIN %s u ON u.%s = t.%s " +
                        "ORDER BY t.%s DESC;",
                SchemDB.ID_TORNEO, SchemDB.T_FECHA, SchemDB.T_ESTADO, SchemDB.T_NOMBRE,
                SchemDB.ID_JUEGO, SchemDB.A_NOMBRE,
                SchemDB.ID_SALA, SchemDB.SL_NOMBRE, SchemDB.SL_TAMANIO,
                SchemDB.ID_USUARIO, SchemDB.U_NOMBRE, SchemDB.U_APELLIDO,
                SchemDB.TAB_TORNEO,
                SchemDB.TAB_JUEGO, SchemDB.ID_ARTICULO, SchemDB.ID_JUEGO, // ← ID_ARTICULO aquí
                SchemDB.TAB_ARTICULO, SchemDB.ID_ARTICULO, SchemDB.ID_ARTICULO,
                SchemDB.TAB_SALA, SchemDB.ID_SALA, SchemDB.ID_SALA,
                SchemDB.TAB_USUARIO, SchemDB.ID_USUARIO, SchemDB.ID_USUARIO,
                SchemDB.T_FECHA
        );
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Juego juego = new Juego();
                juego.setIdArticulo(resultSet.getInt("id_juego"));
                juego.setNombre(resultSet.getString("juego_nombre"));// todo Constructor

                Sala sala = new Sala(
                        resultSet.getInt("id_sala"),
                        resultSet.getString("sala_nombre"),
                        resultSet.getInt("sala_tamanio")
                );// todo Constructor

                Usuario arbitro = new Usuario();
                arbitro.setIdUsuario(resultSet.getLong("id_usuario"));
                arbitro.setNombre(resultSet.getString("arb_nombre"));
                arbitro.setApellido(resultSet.getString("arb_apellido"));// todo Constructor

                TorneoEstadoEnum estado = TorneoEstadoEnum.fromValorDB(
                        resultSet.getString("estado"));

                Torneo torneo = new Torneo();
                torneo.setIdTorneo(resultSet.getInt("id_torneo"));
                torneo.setNombre(resultSet.getString("nombre"));
                torneo.setJuego(juego);
                torneo.setSala(sala);
                torneo.setUsuario(arbitro);
                torneo.setFecha(resultSet.getDate("fecha"));
                torneo.setTorneoEstadoEnum(estado);// todo Constructor

                torneos.add(torneo);
            }
        } catch (SQLException e) {
            System.out.println("Error listadoTorneos: " + e.getMessage());
            e.printStackTrace();
        }
        return torneos;
    }

    @Override
    public boolean crearTorneo(Torneo torneo) {
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s) " +
                "VALUES (?, ?, ?, ?, ?, ?);"
                ,SchemDB.TAB_TORNEO, SchemDB.ID_USUARIO, SchemDB.ID_SALA, SchemDB.ID_JUEGO, SchemDB.T_FECHA, SchemDB.T_ESTADO, SchemDB.T_NOMBRE);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, torneo.getUsuario().getIdUsuario());
            preparedStatement.setInt(2, torneo.getSala().getIdSala());
            preparedStatement.setInt(3, torneo.getJuego().getIdArticulo());
            preparedStatement.setDate(4, torneo.getFecha());
            preparedStatement.setString(5, TorneoEstadoEnum.CREADO.getValorDB());
            preparedStatement.setString(6, torneo.getNombre());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error crearTorneo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarEstado(int idTorneo, TorneoEstadoEnum estado) {
        String query = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                SchemDB.TAB_TORNEO, SchemDB.T_ESTADO, SchemDB.ID_TORNEO);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, estado.getValorDB());
            preparedStatement.setInt(2, idTorneo);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error actualizarEstado: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
