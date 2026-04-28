package org.example.retropvpadmin.dao.impl;

import org.example.retropvpadmin.config.ConexionBBDD;
import org.example.retropvpadmin.config.SchemDB;
import org.example.retropvpadmin.dao.interfaces.IArticuloDao;
import org.example.retropvpadmin.model.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ArticuloDaoImpl implements IArticuloDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ArticuloDaoImpl() {
        connection = ConexionBBDD.getConnection();
    }

    @Override
    public List<Articulo> listadoArticulos() {
        List<Articulo> articulos = new ArrayList<>();
        String query = String.format("SELECT \n" +
                "    a.%s,\n" +
                "    a.%s,\n" +
                "    a.%s,\n" +
                "    `a`.`%s`,\n" +
                "    s.%s,\n" +
                "    s.%s,\n" +
                "    c.%s AS consola_anio,\n" +
                "    j.%s       AS juego_id_consola,\n" +
                "    j.%s AS juego_anio,\n" +
                "    j.%s,\n" +
                "    ac.%s      AS accesorio_id_consola,\n" +
                "    CASE \n" +
                "        WHEN c.%s  IS NOT NULL THEN 'consola'\n" +
                "        WHEN j.%s  IS NOT NULL THEN 'juego'\n" +
                "        WHEN ac.%s IS NOT NULL THEN 'accesorio'\n" +
                "        ELSE 'desconocido'\n" +
                "    END AS tipo\n" +
                "FROM %s a\n" +
                "LEFT JOIN %s     s  ON s.%s  = a.%s\n" +
                "LEFT JOIN %s   c  ON c.%s  = a.%s\n" +
                "LEFT JOIN %s     j  ON j.%s  = a.%s\n" +
                "LEFT JOIN %s ac ON ac.%s = a.%s\n" +
                "ORDER BY tipo, a.%s;", SchemDB.ID_ARTICULO, SchemDB.A_NOMBRE, SchemDB.A_PRECIO, SchemDB.A_DESCRIPCION,
                SchemDB.ID_STOCK, SchemDB.S_CANTIDAD, SchemDB.ANO_LANZAMIENTO, SchemDB.ID_CONSOLA, SchemDB.ANO_LANZAMIENTO, SchemDB.J_JUGADORES_PVP,
                SchemDB.ID_CONSOLA, SchemDB.ID_ARTICULO, SchemDB.ID_ARTICULO, SchemDB.ID_ARTICULO, SchemDB.TAB_ARTICULO, SchemDB.TAB_STOCK,
                SchemDB.ID_ARTICULO, SchemDB.ID_ARTICULO, SchemDB.TAB_CONSOLA, SchemDB.ID_ARTICULO, SchemDB.ID_ARTICULO,
                SchemDB.TAB_JUEGO, SchemDB.ID_ARTICULO, SchemDB.ID_ARTICULO, SchemDB.TAB_ACCESORIOS, SchemDB.ID_ARTICULO, SchemDB.ID_ARTICULO,
                SchemDB.ID_ARTICULO
                );
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer idArticulo = resultSet.getInt(SchemDB.ID_ARTICULO);
                String nombre = resultSet.getString(SchemDB.A_NOMBRE);
                BigDecimal precio = resultSet.getBigDecimal(SchemDB.A_PRECIO);
                String description = resultSet.getString(SchemDB.A_DESCRIPCION);
                Consola consola = null;
                Juego juego = null;
                Accesorio accesorio = null;
                String tipo = resultSet.getString("tipo");
                switch (tipo) {
                    case "consola" -> consola = new Consola(
                            resultSet.getInt(SchemDB.ID_ARTICULO),
                            resultSet.getInt("consola_anio"));
                    case "juego" -> {
                        Consola consolaJuego = new Consola(resultSet.getInt("juego_id_consola"));
                        juego = new Juego(resultSet.getInt(SchemDB.ID_ARTICULO), consolaJuego,
                                resultSet.getInt("juego_anio"), resultSet.getInt(SchemDB.J_JUGADORES_PVP));
                    }
                    case "accesorio" -> {
                        Consola consolaAccesorio = new Consola(resultSet.getInt("accesorio_id_consola"));
                        accesorio = new Accesorio(resultSet.getInt(SchemDB.ID_ARTICULO), consolaAccesorio);
                    }
                }
                Set<Stock> stocks = new HashSet<>(Arrays
                        .asList(new Stock(resultSet.getInt(SchemDB.ID_STOCK), resultSet.getInt(SchemDB.S_CANTIDAD))));
                Articulo articulo = new Articulo(idArticulo, nombre, precio, description, consola, juego, accesorio, stocks);
                articulos.add(articulo);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return articulos;
    }
}
