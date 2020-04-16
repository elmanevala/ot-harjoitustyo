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

    private void insertTopPlayer(int gridsize, int rowsize, String name, int moves) throws SQLException {
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
    
    public boolean isInTopFive(int rowsize, int gridsize, int moves) throws SQLException{
        startConnection();
        stmt = connection.prepareStatement("SELECT * FROM TopLists");
//        stmt.setInt(1, gridsize);
//        stmt.setInt(2, rowsize);
//        stmt.setInt(3, moves);
        ResultSet leastMoveWins = stmt.executeQuery();
        
        System.out.println("Tsekkaamassa top-listaa");
        
        while (leastMoveWins.next()){
            System.out.println(leastMoveWins.getString("name"));
        }
        
        if (leastMoveWins.getFetchSize() >= 5){
            return false;
        } else {
            return true;
        }
    }
        
    
}
