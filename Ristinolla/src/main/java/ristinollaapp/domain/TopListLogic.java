package ristinollaapp.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import ristinollaapp.dao.TopListsDao;

public class TopListLogic {

    private TopListsDao dao;
    private int gridsize;
    private int rowsize;
    private int moves;

    public TopListLogic(String dbname) {
        this.dao = new TopListsDao(dbname);
    }

    public TopListLogic(int gridsize, int rowsize, int moves, String dbname) {
        this.dao = new TopListsDao(dbname);
        this.gridsize = gridsize;
        this.rowsize = rowsize;
        this.moves = moves;
    }

    public boolean isInTopFive() {
        if (this.dao.isInTopFive(this.gridsize, this.rowsize, this.moves)) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> topFive() {
        ArrayList<String> topfive = dao.topFive(this.gridsize, this.rowsize, this.moves);
        return topfive;
    }

    public void addName(String name) {
        this.dao.insertTopPlayer(this.gridsize, this.rowsize, name, this.moves);
    }

    public int getRowSize() {
        return this.rowsize;
    }

    public int getgridSize() {
        return this.gridsize;
    }

    public String mostPopularSize() {
        String[] parts = this.dao.mostPopularSize().split(",");

        if (parts[0].equals("no games")) {
            return "Ei dataa";
        }
        return parts[0];
    }

    public String popularSizeQuantity() {
        String[] parts = this.dao.mostPopularSize().split(",");

        if (parts.length < 2) {
            return "0";
        }
        return parts[1];
    }

    public String mostPopularRow() {
        String[] parts = this.dao.mostPopularRow().split(",");

        if (parts[0].equals("no games")) {
            return "Ei dataa";
        }
        return parts[0];
    }

    public String popularRowQuantity() {
        String[] parts = this.dao.mostPopularRow().split(",");

        if (parts.length < 2) {
            return "0";
        }
        return parts[1];
    }

    public String games() {
        return this.dao.gamesPlayed();
    }

    public String mostPopularCombSize() {
        String[] parts = this.dao.mostPopularGame().split(",");
        if (parts[0].equals("no games")) {
            return "-";
        }
        return parts[0];
    }

    public String mostPopularCombRow() {
        String[] parts = this.dao.mostPopularGame().split(",");
        return parts[1];
    }

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

    public String popularPlayed() {
        return this.dao.mostPopularPlayed();
    }
}
