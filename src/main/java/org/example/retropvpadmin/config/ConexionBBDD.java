package org.example.retropvpadmin.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {

        private static Connection connection;

        public static Connection getConnection(){
            if (connection == null) {
                createConnection();
            }
            return connection;
        }

    private static void createConnection() {
            String user = "root";
            String pass = "root";
            String database = "retropvp";
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/"+database,user,pass);
        } catch (SQLException e) {
            System.out.println("Error en la conexión con la bbdd");
            System.out.println(e.getMessage());
        }
    }

}
