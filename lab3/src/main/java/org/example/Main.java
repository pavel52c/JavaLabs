package org.example;

import org.example.dbworker.DBWorker;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        DBWorker dbWorker = new DBWorker("data.csv");
    }
}