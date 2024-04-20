package org.example.dbWorker;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    private final String userName = "root";
    private final String password = "user";
    private final String url = "jdbc:mysql://localhost:3306/lab3";
    private Connection connection;

    public DBConnector() {
        try {
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection successful...");
        } catch (Exception e) {
            System.out.println("Connection failed...");
        }

    }

    public Connection getConnection() {
        return connection;
    }
}
