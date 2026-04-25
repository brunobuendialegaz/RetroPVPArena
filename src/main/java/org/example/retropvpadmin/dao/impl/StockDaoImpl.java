package org.example.retropvpadmin.dao.impl;

import org.example.retropvpadmin.config.ConexionBBDD;
import org.example.retropvpadmin.config.SchemDB;
import org.example.retropvpadmin.dao.interfaces.IStockDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockDaoImpl implements IStockDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public StockDaoImpl(){
        connection = ConexionBBDD.getConnection();
    }

    @Override
    public long articulosCatalogo() {
        String query = String.format("select count(distinct a.%s) as articulos_disponibles\n" +
                "    from %s a\n" +
                "    join %s s on a.%s = s.%s \n" +
                "    where s.%s > 0;", SchemDB.ID_ARTICULO, SchemDB.TAB_ARTICULO,
                SchemDB.TAB_STOCK, SchemDB.ID_ARTICULO, SchemDB.ID_ARTICULO,
                SchemDB.S_CANTIDAD);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("articulos_disponibles");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public long articulosStockBajo() {
        String query = String.format("select count(distinct a.%s) as articulos_disponibles\n" +
                        "    from %s a\n" +
                        "    join %s s on a.%s = s.%s \n" +
                        "    where s.%s <= 2;", SchemDB.ID_ARTICULO, SchemDB.TAB_ARTICULO,
                SchemDB.TAB_STOCK, SchemDB.ID_ARTICULO, SchemDB.ID_ARTICULO,
                SchemDB.S_CANTIDAD);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("articulos_disponibles");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
