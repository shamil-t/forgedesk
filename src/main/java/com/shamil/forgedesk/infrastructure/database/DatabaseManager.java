package com.shamil.forgedesk.infrastructure.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {


    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(SqliteConfig.URL);
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
