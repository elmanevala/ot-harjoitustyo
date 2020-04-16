package ristinollaapp.domain;

import java.sql.SQLException;
import ristinollaapp.doa.TopLists;

public class TopListLogic {

    private TopLists dao;

    public TopListLogic(int gridsize, int rowsize, int moves) throws SQLException {
        this.dao = new TopLists();
    }

    public boolean isInTopFive(int gridsize, int rowsize, int moves) throws SQLException{
        if(this.dao.isInTopFive(gridsize, rowsize, moves)){
            return true;
        } else {
            return false;
        }
    }
}
