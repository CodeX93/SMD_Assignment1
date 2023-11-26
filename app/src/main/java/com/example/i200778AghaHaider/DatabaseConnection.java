package com.example.i200778AghaHaider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/spot-it_user";

    private static final String DB_USER = "root";  // Or use your configured username
    private static final String DB_PASSWORD = "";    // Or use your configured password


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}

