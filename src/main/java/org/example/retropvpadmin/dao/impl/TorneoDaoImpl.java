package org.example.retropvpadmin.dao.impl;

import org.example.retropvpadmin.config.ConexionBBDD;
import org.example.retropvpadmin.config.SchemDB;
import org.example.retropvpadmin.dao.interfaces.ITorneoDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
