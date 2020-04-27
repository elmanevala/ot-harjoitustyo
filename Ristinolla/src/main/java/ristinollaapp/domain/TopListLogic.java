package ristinollaapp.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import ristinollaapp.dao.TopListsDao;

public class TopListLogic {

    private TopListsDao dao;
    private int gridsize;
    private int rowsize;
    private int moves;

    /**
     * Constructor for when TopLists aren't used for adding a game. For example,
     * when statistics are viewed.
     *
     * @param dbname name of the sqlite-file
     *
     */
    public TopListLogic(String dbname) {
        this.dao = new TopListsDao(dbname);
    }

    /**
     * Constructor for when TopLists are used to add a game.
     *
     * @param gridsize gridsize of the played game
     * @param rowsize rowsize of the played game
     * @param moves moves needed to win the played game
     * @param dbname name of the sqlite-file
     *
     * *
     */
    public TopListLogic(int gridsize, int rowsize, int moves, String dbname) {
        this.dao = new TopListsDao(dbname);
        this.gridsize = gridsize;
        this.rowsize = rowsize;
        this.moves = moves;
    }

    /**
     * Checking if the game given in the constructor is in the top five
     *
     * @return true if game is in the top five
     */
    public boolean isInTopFive() {
        if (this.dao.isInTopFive(this.gridsize, this.rowsize, this.moves)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieving the top five games with the same customization as the game
     * given in the constructor
     *
     * @return an ArrayList with the top five games
     */
    public ArrayList<String> topFive() {
        ArrayList<String> topfive = dao.topFive(this.gridsize, this.rowsize, this.moves);
        return topfive;
    }

    /**
     * Adding a name to the table with other information given in the
     * constructor
     *
     * @param name name of the entry
     */
    public void addName(String name) {
        this.dao.insertTopPlayer(this.gridsize, this.rowsize, name, this.moves);
    }

    public int getRowSize() {
        return this.rowsize;
    }

    public int getgridSize() {
        return this.gridsize;
    }

    /**
     * Checking what's most popular gridsize in the table
     *
     * @return the most popular size as a String or if no games have been played
     * "Ei dataa"
     */
    public String mostPopularSize() {
        String[] parts = this.dao.mostPopularSize().split(",");

        if (parts[0].equals("no games")) {
            return "Ei dataa";
        }
        return parts[0];
    }

    /**
     * Checking how many games have been played with the most popular size
     *
     * @return quantity of games with the most popular size as a String
     */
    public String popularSizeQuantity() {
        String[] parts = this.dao.mostPopularSize().split(",");

        if (parts.length < 2) {
            return "0";
        }
        return parts[1];
    }

    /**
     * Checking what's most popular rowsize in the table
     *
     * @return the most popular rowsize as a String or if no games have been
     * played "Ei dataa"
     */
    public String mostPopularRow() {
        String[] parts = this.dao.mostPopularRow().split(",");

        if (parts[0].equals("no games")) {
            return "Ei dataa";
        }
        return parts[0];
    }

    /**
     * Checking how many games have been played with the most popular rowsize.
     *
     * @return quantity of games with the most popular rowsize as a String
     */
    public String popularRowQuantity() {
        String[] parts = this.dao.mostPopularRow().split(",");

        if (parts.length < 2) {
            return "0";
        }
        return parts[1];
    }

    /**
     * Checking how many games have been played in total.
     *
     * @return quantity of played games as a String
     */
    public String games() {
        return this.dao.gamesPlayed();
    }

    /**
     * Checking what's most popular combination of gridsizes
     * and rowsizes.
     *
     * @return the gridsize of the most popular combination as a a String
     */
    public String mostPopularCombSize() {
        String[] parts = this.dao.mostPopularGame().split(",");
        if (parts[0].equals("no games")) {
            return "-";
        }
        return parts[0];
    }

    
     /**
     * Checking what's most popular combination of gridsizes
     * and rowsizes.
     *
     * @return the rowsize of the most popular combination as a a String
     */
    public String mostPopularCombRow() {
        String[] parts = this.dao.mostPopularGame().split(",");
        return parts[1];
    }

     /**
     * Checking the average amount of moves needed to win a game.
     * Shortens the average String to a maximum a four characters.
     *
     * @return the average
     */
    public String averageMoves() {
        String average = String.valueOf(this.dao.averageMoves());

        if (average.equals("NaN")) {
            return "-";
        }

        String firstFourChars = "";

        if (average.length() > 4) {
            firstFourChars = average.substring(0, 4);
        } else {
            firstFourChars = average;
        }

        return firstFourChars;
    }

     /**
     * Checking how many times the most popular game combination 
     * has been played.
     *
     * @return quantity of games with the most popular combination
     */
    public String popularPlayed() {
        return this.dao.mostPopularPlayed();
    }
}
