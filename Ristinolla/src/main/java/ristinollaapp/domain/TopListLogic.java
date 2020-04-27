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
        return this.dao.mostPopularSize();
    }

    public String mostPopularRow() {
        return this.dao.mostPopularRow();
    }

    public String games() {
        return this.dao.gamesPlayed();
    }

    public String mostPopularCombination() {
        return this.dao.mostPopularGame();
    }

    public double averageMoves() {
        return this.dao.averageMoves();
    }

    public String popularPlayed() {
        return this.dao.mostPopularPlayed();
    }
}
