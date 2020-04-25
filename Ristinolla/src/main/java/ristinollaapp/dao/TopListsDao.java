package ristinollaapp.dao;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TopListsDao {

    private Connection connection;
    private PreparedStatement stmt;
    private Statement s;
    private String bdname;

    public TopListsDao(String dbname) throws SQLException {
        this.bdname = dbname;
        initializeDB();
    }

    public void initializeDB() throws SQLException {
        startConnection();
        s.execute("CREATE TABLE IF NOT EXISTS TopLists (id INTEGER PRIMARY KEY, name TEXT NOT NULL, gridsize INTEGER, rowsize INTEGER, moves INTEGER);");
        closeConnection();
    }

    public void startConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + this.bdname);
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
        stmt = connection.prepareStatement("SELECT name, moves FROM TopLists WHERE gridsize=? AND rowsize=? AND moves<=? ORDER BY moves, id DESC LIMIT 5");
        stmt.setInt(1, gridsize);
        stmt.setInt(2, rowsize);
        stmt.setInt(3, moves);
        ResultSet leastMoveWins = stmt.executeQuery();

        ArrayList<String> topfive = new ArrayList<>();

        while (leastMoveWins.next()) {
            topfive.add(leastMoveWins.getString("name") + "," + leastMoveWins.getString("moves"));
        }

        closeConnection();
        return topfive;
    }

    public void clear() throws SQLException {
        startConnection();
        s.execute("DROP TABLE IF EXISTS TopLists;");
        closeConnection();
        initializeDB();
    }

    public String mostPopularSize() throws SQLException {
        startConnection();
        stmt = connection.prepareStatement("SELECT gridsize, COUNT(gridsize) AS mostPopular FROM TopLists GROUP BY gridsize ORDER BY mostPopular DESC LIMIT 1");

        ResultSet mostPopular = stmt.executeQuery();

        String sizeAndQuantity = "no games";
        while (mostPopular.next()) {
            sizeAndQuantity = mostPopular.getString("gridsize") + "," + mostPopular.getString("mostPopular");
        }

        closeConnection();
        return sizeAndQuantity;
    }

    public String mostPopularRow() throws SQLException {
        startConnection();
        stmt = connection.prepareStatement("SELECT rowsize, COUNT(rowsize) AS mostPopular FROM TopLists GROUP BY rowsize ORDER BY mostPopular DESC LIMIT 1");

        ResultSet mostPopular = stmt.executeQuery();

        String sizeAndQuantity = "no games";
        while (mostPopular.next()) {
            sizeAndQuantity = mostPopular.getString("rowsize") + "," + mostPopular.getString("mostPopular");
        }

        closeConnection();
        return sizeAndQuantity;
    }

    public String gamesPlayed() throws SQLException {
        startConnection();

        stmt = connection.prepareStatement("SELECT COUNT(*) AS games FROM TopLists");

        ResultSet mostPopular = stmt.executeQuery();

        String games = "no games";
        while (mostPopular.next()) {
            games = mostPopular.getString("games");
        }

        closeConnection();

        return games;
    }

    public String mostPopularGame() throws SQLException {
        startConnection();

        stmt = connection.prepareStatement("SELECT gridsize, rowsize, COUNT(*) FROM TopLists GROUP BY 10*gridsize+rowsize ORDER BY COUNT(*) DESC LIMIT 1");

        ResultSet mostPopular = stmt.executeQuery();

        String games = "";
        while (mostPopular.next()) {
            games = mostPopular.getString("gridsize") + "," + mostPopular.getString("rowsize");
        }

        closeConnection();

        return games;
    }

    public String mostPopularPlayed() throws SQLException {
        String[] sizeAndRow = mostPopularGame().split(",");
        startConnection();

        stmt = connection.prepareStatement("SELECT COUNT(*) AS sum FROM TopLists WHERE rowsize=? AND gridsize=?");
        stmt.setInt(1, Integer.valueOf(sizeAndRow[1]));
        stmt.setInt(2, Integer.valueOf(sizeAndRow[0]));

        ResultSet played = stmt.executeQuery();

        String games = "";
        while (played.next()) {
            games = played.getString("sum");
        }

        closeConnection();

        return games;
    }

    public double averageMoves() throws SQLException {
        startConnection();

        stmt = connection.prepareStatement("SELECT moves FROM TopLists");

        ResultSet averageMoves = stmt.executeQuery();

        double sum = 0;
        double i = 0;
        while (averageMoves.next()) {
            sum = sum + averageMoves.getDouble("moves");
            i++;
        }

        double average = 1.0 * sum / i;

        closeConnection();

        return average;
    }

}
