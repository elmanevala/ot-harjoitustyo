package ristinollaapp.domain;

import java.sql.SQLException;
import ristinollaapp.doa.TopLists;

public class TopListLogic {

    private TopLists dao;

    public TopListLogic(int gridsize, int rowsize, int moves) throws SQLException {
        this.dao = new TopLists();
    }

    
}
