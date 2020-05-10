package ristinollaapp.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Has methods to retrieve and store data from played games.
 *
 */
public class TopListsDao {

    private Connection connection;
    private PreparedStatement stmt;
    private Statement s;
    private String dbname;

    /**
     * Sets the name of the file that is used to store the data. Using the
     * method initializeDB() creates a new table if one doesn't exist.
     */
    public TopListsDao(String dbname) {
        this.dbname = dbname;

        initializeDB();

    }

    /**
     * Creates a new table for games, if it doesn't already exist.
     */
    public void initializeDB() {
        try {
            startConnection();
            s.execute("CREATE TABLE IF NOT EXISTS TopLists (id INTEGER PRIMARY KEY, name TEXT NOT NULL, gridsize INTEGER, rowsize INTEGER, moves INTEGER);");
            closeConnection();
        } catch (SQLException e) {
            System.out.println("Taulukkoa luodessa tapahtui virhe!");
        }
    }

    /**
     * Stars a new connection to the database.
     */
    public void startConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + this.dbname);
            s = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Yhteyttä luodessa tapahtui virhe!");
        }
    }

    /**
     * Closes the connection to a connected database.
     */
    public void closeConnection() {
        try {
            s.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Yhteyttä sulkiessa tapahtui virhe!");
        }
    }

    /**
     * Inserts a new game to the table.
     *
     * @param gridsize size of the grid in the game
     * @param rowsize size of the winning row in the game
     * @param name name of the game's winner
     * @param moves how many moves it took to win the game
     */
    public void insertTopPlayer(int gridsize, int rowsize, String name, int moves) {
        try {
            startConnection();
            stmt = connection.prepareStatement("INSERT INTO TopLists (name, gridsize, rowsize, moves) VALUES (?,?,?,?);");
            stmt.setString(1, name);
            stmt.setInt(2, gridsize);
            stmt.setInt(3, rowsize);
            stmt.setInt(4, moves);
            stmt.executeUpdate();
            stmt.close();
            closeConnection();
        } catch (SQLException e) {
            System.out.println("Pelaajaa taulukkoon lisätessä tapahtui virhe!");
        }
    }

    /**
     * Checks if the game that was played, was won with less moves than the top
     * five games with the same grid and row sizes in the table.
     *
     *
     * @param gridsize size of the grid in the game
     * @param rowsize size of the winning row in the game
     * @param moves how many moves it took to win the game
     *
     * @return true, if game is in the top five games.
     */
    public boolean isInTopFive(int gridsize, int rowsize, int moves) {
        try {
            startConnection();
            stmt = connection.prepareStatement("SELECT COALESCE(COUNT(name), 0) as number FROM TopLists WHERE gridsize=? AND rowsize=? AND moves<? AND name!=?");
            stmt.setInt(1, gridsize);
            stmt.setInt(2, rowsize);
            stmt.setInt(3, moves);
            stmt.setString(4, "");
            ResultSet leastMoveWins = stmt.executeQuery();

            int number = leastMoveWins.getInt("number");

            if (number >= 5) {
                closeConnection();
                return false;
            } else {
                closeConnection();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Tapahtui virhe, kun tarkistettiin kuuluuko pelaaja top viiteen!");
            return false;
        }

    }

    /**
     * Gets the top five games, that were won with the least amount of moves. If
     * games have the same amount of moves, top five is created based on how new
     * the games are. Most recent games go to the top of the list.
     *
     * @param gridsize size of the grid in the game-
     * @param rowsize size of the winning row in the game.
     * @param moves how many moves it took to win the game.
     *
     * @return The top five games from the table as an ArrayList
     */
    public ArrayList<String> topFive(int gridsize, int rowsize, int moves) {
        try {
            startConnection();
            stmt = connection.prepareStatement("SELECT name, moves FROM TopLists WHERE gridsize=? AND rowsize=? AND moves<=? and name!=? ORDER BY moves, id DESC LIMIT 5");
            stmt.setInt(1, gridsize);
            stmt.setInt(2, rowsize);
            stmt.setInt(3, moves);
            stmt.setString(4, "");
            ResultSet leastMoveWins = stmt.executeQuery();

            ArrayList<String> topfive = new ArrayList<>();

            while (leastMoveWins.next()) {
                topfive.add(leastMoveWins.getString("name") + "," + leastMoveWins.getString("moves"));
            }

            closeConnection();
            return topfive;
        } catch (SQLException e) {
            System.out.println("Top-5 etsiessä tapahtui virhe!");
            return null;
        }
    }

    /**
     * Clears the connected table.
     */
    public void clear() {
        try {
            startConnection();
            s.execute("DROP TABLE IF EXISTS TopLists;");
            closeConnection();
            initializeDB();
        } catch (SQLException e) {
            System.out.println("Taulukkoa tyhjentäessä tapahtui virhe!");
        }
    }

    /**
     * Checks from the table which grid size is the most popular.
     *
     * @return most popular size as a String
     */
    public String mostPopularSize() {
        try {
            startConnection();
            stmt = connection.prepareStatement("SELECT gridsize, COALESCE(COUNT(gridsize), 0) AS mostPopular FROM TopLists GROUP BY gridsize ORDER BY mostPopular DESC LIMIT 1");

            ResultSet mostPopular = stmt.executeQuery();

            String sizeAndQuantity = "no games";
            while (mostPopular.next()) {
                sizeAndQuantity = mostPopular.getString("gridsize") + "," + mostPopular.getString("mostPopular");
            }

            closeConnection();
            return sizeAndQuantity;
        } catch (SQLException e) {
            System.out.println("Suorituinta kokoa etsiessä tapahtui virhe!");
            return null;
        }

    }

    /**
     * Checks from the table which row size is the most popular.
     *
     * @return most popular size as a String
     */
    public String mostPopularRow() {
        try {
            startConnection();
            stmt = connection.prepareStatement("SELECT rowsize, COALESCE(COUNT(rowsize), 0) AS mostPopular FROM TopLists GROUP BY rowsize ORDER BY mostPopular DESC LIMIT 1");

            ResultSet mostPopular = stmt.executeQuery();

            String sizeAndQuantity = "no games";
            while (mostPopular.next()) {
                sizeAndQuantity = mostPopular.getString("rowsize") + "," + mostPopular.getString("mostPopular");
            }

            closeConnection();
            return sizeAndQuantity;
        } catch (SQLException e) {
            System.out.println("Suosituinta voittoriviä etsiessä tapahtui virhe!");
            return null;
        }
    }

    /**
     * Checks from the table how many games have been played in total.
     *
     * @return how many games have been played in total
     */
    public String gamesPlayed() {
        try {
            startConnection();

            stmt = connection.prepareStatement("SELECT COALESCE(COUNT(*), 0) AS games FROM TopLists");

            ResultSet mostPopular = stmt.executeQuery();

            String games = "no games";
            while (mostPopular.next()) {
                games = mostPopular.getString("games");
            }

            closeConnection();

            return games;
        } catch (SQLException e) {
            System.out.println("Pelien lukumäärää etsiessä tapahtui virhe!");
            return null;
        }
    }

    /**
     * Checks from the table which combination of grid and row sizes is the most
     * popular.
     *
     * @return most popular combination as a String
     */
    public String mostPopularGame() {
        try {
            startConnection();

            stmt = connection.prepareStatement("SELECT gridsize, rowsize, COALESCE(COUNT(*), 0) FROM TopLists GROUP BY 10*gridsize+rowsize ORDER BY COUNT(*) DESC LIMIT 1");

            ResultSet mostPopular = stmt.executeQuery();

            String games = "";
            while (mostPopular.next()) {
                games = mostPopular.getString("gridsize") + "," + mostPopular.getString("rowsize");
            }

            if (games.equals("")) {
                games = "0,0";
            }
            closeConnection();

            return games;
        } catch (SQLException e) {
            System.out.println("Suosituinta peliä etsiessä tapahtui virhe!");
            return null;
        }
    }

    /**
     * Checks from the table how many times the most popular combination has
     * been played. Uses the method mostPopularGame() to first get the most
     * popular combination.
     *
     * @return the amount of most popular combination played as a String
     */
    public String mostPopularPlayed() {
        try {
            String[] sizeAndRow = mostPopularGame().split(",");
            startConnection();

            stmt = connection.prepareStatement("SELECT COALESCE(COUNT(*), 0) AS sum FROM TopLists WHERE rowsize=? AND gridsize=?");
            stmt.setInt(1, Integer.valueOf(sizeAndRow[1]));
            stmt.setInt(2, Integer.valueOf(sizeAndRow[0]));

            ResultSet played = stmt.executeQuery();

            String games = "";
            while (played.next()) {
                games = played.getString("sum");
            }

            closeConnection();

            return games;
        } catch (SQLException e) {
            System.out.println("Suosituiman pelin lukumäärää etsiessä tapahtui virhe!");
            return null;
        }
    }

    /**
     * Checks how many moves it takes on average to win a game. Excludes rows
     * with an empty name as they have ended in a draw.
     *
     * @return average moves needed for a win as a double
     */
    public double averageMoves() {
        try {
            startConnection();

            stmt = connection.prepareStatement("SELECT avg(moves) as avg FROM TopLists WHERE name!=?");
            stmt.setString(1, "");

            ResultSet averageMoves = stmt.executeQuery();

            double avg = 0;
            double i = 0;
            while (averageMoves.next()) {
                avg = averageMoves.getDouble("avg");
            }

            closeConnection();
            return avg;
        } catch (SQLException e) {
            System.out.println("Keskiverto voittosiirtoja etsiessä tapahtui virhe!");
            return 0;
        }
    }

}
