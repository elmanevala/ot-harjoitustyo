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

    public void startConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:toplists.db");
        s = connection.createStatement();
    }

    public void closeConnection() throws SQLException {
        s.close();
        connection.close();
    }

    public void insertTopPlayer(int gridsize, int rowsize, String name, int moves) throws SQLException {
        startConnection();
        stmt = connection.prepareStatement("INSERT INTO TopLists (name, gridsize, rowsize, moves) VALUES (?,?,?,?);");
        stmt.setString(1, name);
        stmt.setInt(2, gridsize);
        stmt.setInt(3, rowsize);
        stmt.setInt(4, moves);
        stmt.executeUpdate();
        stmt.close();
        closeConnection();
    }

    public boolean isInTopFive(int gridsize, int rowsize, int moves) throws SQLException {
        startConnection();
        stmt = connection.prepareStatement("SELECT COALESCE(COUNT(name), 0) as number FROM TopLists WHERE gridsize=? AND rowsize=? AND moves<?");
        stmt.setInt(1, gridsize);
        stmt.setInt(2, rowsize);
        stmt.setInt(3, moves);
        ResultSet leastMoveWins = stmt.executeQuery();

        int number = leastMoveWins.getInt("number");

        if (number >= 5) {
            closeConnection();
            return false;
        } else {
            closeConnection();
            return true;
        }
    }

    public ArrayList<String> topFive(int gridsize, int rowsize, int moves) throws SQLException {
        startConnection();
        stmt = connection.prepareStatement("SELECT name, moves FROM TopLists WHERE gridsize=? AND rowsize=? AND moves<? ORDER BY moves LIMIT 5");
        stmt.setInt(1, gridsize);
        stmt.setInt(2, rowsize);
        stmt.setInt(3, moves);
        ResultSet leastMoveWins = stmt.executeQuery();

        ArrayList<String> topfive = new ArrayList<>();
        
        while (leastMoveWins.next()){
            topfive.add("name" + "    " + "moves");
        }
        
        return topfive;
    }

}
