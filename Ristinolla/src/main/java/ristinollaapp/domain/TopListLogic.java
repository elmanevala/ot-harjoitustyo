package ristinollaapp.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import ristinollaapp.doa.TopLists;

public class TopListLogic {

    private TopLists dao;
    private int gridsize;
    private int rowsize;
    private int moves;

    public TopListLogic(int gridsize, int rowsize, int moves, String dbname) throws SQLException {
        this.dao = new TopLists(dbname);
        this.gridsize = gridsize;
        this.rowsize = rowsize;
        this.moves = moves;
    }

    public boolean isInTopFive() throws SQLException {
        if (this.dao.isInTopFive(this.gridsize, this.rowsize, this.moves)) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> topFive() throws SQLException {
        ArrayList<String> topfive = dao.topFive(this.gridsize, this.rowsize, this.moves);
        return topfive;
    }

    public void addName(String name) throws SQLException {
        this.dao.insertTopPlayer(this.gridsize, this.rowsize, name, this.moves);
    }

    public int getRowSize() {
        return this.rowsize;
    }

    public int getgridSize() {
        return this.gridsize;
    }
}
