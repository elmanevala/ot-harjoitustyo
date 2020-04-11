package ristinollaapp.doa;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopLists {

    private Connection connection;
    private PreparedStatement stmt;
    private Statement s;

    public TopLists() throws SQLException {
        initializeDB();
    }

    public void initializeDB() throws SQLException {
        System.out.println("täällä");
        startConnection();
        s.execute("CREATE TABLE IF NOT EXISTS TopLists (id INTEGER PRIMARY KEY, name TEXT NOT NULL, gridsize INTEGER, rowsize INTEGER, moves INTEGER);");
        closeConnection();
    }
    
        private void startConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:toplists.db");
        s = connection.createStatement();
    }

    private void closeConnection() throws SQLException {
        s.close();
        connection.close();
    }

}
