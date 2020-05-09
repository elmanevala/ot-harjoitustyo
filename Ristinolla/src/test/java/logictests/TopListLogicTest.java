package logictests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
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
public class TopListLogicTest {

    TopListLogic logic;
    TopListsDao dao;

    public TopListLogicTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.dao = new TopListsDao(dbname());
        this.logic = new TopListLogic(4, 3, 3, dbname());
        this.dao.clear();
    }

    public String dbname() {
        try {
            Properties properties = new Properties();

            properties.load(new FileInputStream("config.properties"));

            String testFile = properties.getProperty("testFile");

            return testFile;
        } catch (IOException e) {
            System.out.println("Tiedostoa ei löydy!");
            return null;
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void trueIfIsInTopFive() {
        assertEquals(true, logic.isInTopFive());
    }

    @Test
    public void falseIfNotInTopFive() {
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        assertEquals(false, logic.isInTopFive());
    }

    @Test
    public void givesFiveTopGames() {
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        assertEquals(5, logic.topFive().size());
    }

    @Test
    public void givesTopGamesWhenUnderFive() {
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
    public void findsGameTotal() {
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 4, "epe", 3);
        this.logic.addName("epe");

        assertEquals("4", logic.games());
    }

    @Test
    public void findsMostPopularSize() {
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.logic.addName("epe");

        assertEquals("4", logic.mostPopularSize());
    }

    @Test
    public void findMostPopularSizeTotal() {
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(3, 3, "epe", 0);

        assertEquals("2", logic.popularSizeQuantity());
    }

    @Test
    public void findsMostPopularRow() {
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.logic.addName("epe");

        assertEquals("3", logic.mostPopularRow());
    }

    @Test
    public void findsMostPopularRowTotal() {
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.logic.addName("epe");

        assertEquals("3", logic.popularRowQuantity());
    }

    @Test
    public void findsCombinationSize() {
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(3, 4, "epe", 3);
        this.dao.insertTopPlayer(3, 5, "epe", 3);
        this.dao.insertTopPlayer(3, 1, "epe", 3);
        this.dao.insertTopPlayer(3, 2, "epe", 3);
        this.logic.addName("epe");

        assertEquals("4", logic.mostPopularCombSize());
    }

    @Test
    public void findsCombinationRow() {
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(3, 4, "epe", 3);
        this.dao.insertTopPlayer(3, 5, "epe", 3);
        this.dao.insertTopPlayer(3, 1, "epe", 3);
        this.dao.insertTopPlayer(3, 2, "epe", 3);
        this.logic.addName("epe");

        assertEquals("3", logic.mostPopularCombRow());
    }

    @Test
    public void findsCombinationTotal() {
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(3, 4, "epe", 3);
        this.dao.insertTopPlayer(3, 5, "epe", 3);
        this.dao.insertTopPlayer(3, 1, "epe", 3);
        this.dao.insertTopPlayer(3, 2, "epe", 3);

        assertEquals("2", logic.popularPlayed());
    }

    @Test
    public void getRightAverage() {
        this.dao.insertTopPlayer(4, 3, "epe", 1);
        this.dao.insertTopPlayer(4, 3, "epe", 1);
        this.dao.insertTopPlayer(3, 4, "epe", 1);
        this.dao.insertTopPlayer(3, 5, "epe", 3);
        this.dao.insertTopPlayer(3, 1, "epe", 3);
        this.dao.insertTopPlayer(3, 2, "epe", 3);

        assertEquals("2.0", logic.averageMoves());
    }

    @Test
    public void emptyNameExcludesFromAverage() {
        this.dao.insertTopPlayer(4, 3, "epe", 2);
        this.dao.insertTopPlayer(4, 3, "", 0);

        assertEquals("2.0", logic.averageMoves());
    }

    @Test
    public void emptyTableReturnZeroGames() {
        assertEquals("0", logic.games());
    }

    @Test
    public void emptyTableReturnNoDataSizes() {
        assertEquals("Ei dataa", logic.mostPopularSize());
    }

    @Test
    public void emptyTableReturnNoDataRows() {
        assertEquals("Ei dataa", logic.mostPopularRow());
    }

    @Test
    public void emptyTableReturnNoAverage() {
        assertEquals("-", logic.averageMoves());
    }

    @Test
    public void emptyTableReturnZeroCombinationsSize() {
        assertEquals("0", logic.mostPopularCombSize());
    }

    @Test
    public void emptyTableReturnZeroCombinationRow() {
        assertEquals("0", logic.mostPopularCombRow());
    }

    @Test
    public void emptyTableReturnZeroAllQuantaties() {
        assertEquals("0", logic.games());
        assertEquals("0", logic.popularPlayed());
        assertEquals("0", logic.popularRowQuantity());
        assertEquals("0", logic.popularSizeQuantity());
    }

}
