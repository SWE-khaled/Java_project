package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // The database is a single file inside the Data folder
    private static final String URL =
            "jdbc:sqlite:Data/students.db";

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(URL);
    }
}
