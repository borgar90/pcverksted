package com.pcverksted.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String URL = "jdbc:postgresql://localhost:5432/pcverksted";
    private static final String USER = "postgres";  // Replace with your DB username
    private static final String PASSWORD = "dbpsw_123";  // Replace with your DB password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}