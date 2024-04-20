package org.example;

import org.example.dbWorker.DBWorker;

import java.io.IOException;
import java.sql.*;

public class ApplicationStarter {
    public static void main(String[] args) throws IOException, SQLException {
        DBWorker dbWorker = new DBWorker("lab3.csv");
    }
}
