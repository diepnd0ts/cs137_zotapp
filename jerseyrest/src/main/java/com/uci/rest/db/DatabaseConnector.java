package com.uci.rest.db;

import java.sql.*;

import static com.uci.rest.db.DatabaseConfig.*;

/**
 * Created by tariqibrahim on 5/28/17.
 */
public class DatabaseConnector {


    private DatabaseConnector() {

    }

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection("jdbc:mysql://" + getHost() + "/" + getDatabaseName(),
                    getUser(), getPassword());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


}
