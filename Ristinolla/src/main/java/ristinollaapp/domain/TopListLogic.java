package ristinollaapp.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import ristinollaapp.doa.TopLists;

public class TopListLogic {

    private TopLists dao;

    public TopListLogic(int gridsize, int rowsize, int moves) throws SQLException {
        this.dao = new TopLists();
    }

    public boolean isInTopFive(int gridsize, int rowsize, int moves) throws SQLException {
        if (this.dao.isInTopFive(gridsize, rowsize, moves)) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> topFive(int gridsize, int rowsize, int moves) throws SQLException {
        ArrayList<String> topfive = dao.topFive(gridsize, rowsize, moves);
        return topfive;
    }

    public void addName(int gridsize, int rowsize, String name, int moves) throws SQLException {
        this.dao.insertTopPlayer(gridsize, rowsize, name, moves);
    }
}
