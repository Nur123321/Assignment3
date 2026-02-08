package com.assignment3.util;

import com.assignment3.exception.DatabaseOperationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    private final String url;
    private final String user;
    private final String password;

    private DatabaseConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static DatabaseConnection initialize(String url, String user, String password) {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection(url, user, password);
                }
            }
        }
        return instance;
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            throw new IllegalStateException("DatabaseConnection has not been initialized");
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException exception) {
            throw new DatabaseOperationException("Failed to open database connection", exception);
        }
    }
}
