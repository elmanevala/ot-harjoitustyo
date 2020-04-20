/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ristinollaapp.dao.TopListsDao;
import ristinollaapp.domain.TopListLogic;

/**
 *
 * @author elmaneva
 */
public class TopLIstLogicTest {

    TopListLogic logic;
    TopListsDao dao;

    public TopLIstLogicTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SQLException {
        this.dao = new TopListsDao("test.db");
        this.logic = new TopListLogic(4, 3, 3, "test.db");
        this.dao.clear();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void trueIfIsInTopFive() throws SQLException {
        assertEquals(true, logic.isInTopFive());
    }

    @Test
    public void falseIfNotInTopFive() throws SQLException {
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        assertEquals(false, logic.isInTopFive());
    }

    @Test
    public void givesFiveTopGames() throws SQLException {
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        assertEquals(5, logic.topFive().size());
    }

    @Test
    public void givesTopGamesWhenUnderFive() throws SQLException {
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);

        assertEquals(2, logic.topFive().size());
    }

    @Test
    public void addsAName() throws SQLException {
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.logic.addName("epe");

        assertEquals(3, logic.topFive().size());
    }

    @Test
    public void hello() {
    }
}
