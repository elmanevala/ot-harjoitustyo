package daotests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ristinollaapp.dao.TopListsDao;

/**
 *
 * @author elmaneva
 */
public class TopListsDaoTest {

    TopListsDao dao;

    public TopListsDaoTest() {
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
    public void insertsAGame() {
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        assertEquals(4, this.dao.topFive(4, 3, 4).size());
    }

    @Test
    public void trueIfInTopFive() {
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        assertEquals(true, this.dao.isInTopFive(4, 3, 4));
    }

    @Test
    public void falseIfNotInTopFive() {
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        assertEquals(false, this.dao.isInTopFive(4, 3, 5));
    }

    @Test
    public void returnsTopFive() {
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        assertEquals(5, this.dao.topFive(4, 3, 4).size());
    }

    @Test
    public void clearsTable() {
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.clear();
        assertEquals(0, this.dao.topFive(4, 3, 4).size());
    }

    @Test
    public void returnsMostPopularSizeAndQuantity() {
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        assertEquals("4,5", this.dao.mostPopularSize());
    }

    @Test
    public void returnsMostPopularRowAndQuantity() {
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        assertEquals("3,5", this.dao.mostPopularRow());
    }

    @Test
    public void returnsQuantityOfAllPlayedGames() {
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        assertEquals("5", this.dao.gamesPlayed());
    }

    @Test
    public void returnsMostPopularCombination() {
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        assertEquals("4,3", this.dao.mostPopularGame());
    }

    @Test
    public void returnsMostPopularCombinationQuantity() {
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        assertEquals("5", this.dao.mostPopularPlayed());
    }

    @Test
    public void returnsAverageMoves() {
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        this.dao.insertTopPlayer(4, 3, "epe", 4);
        assertEquals(4, this.dao.averageMoves(), 1);
    }

    @Test
    public void whenTableEmptyNoGames() {
        assertEquals("0", this.dao.gamesPlayed());
    }

    @Test
    public void whenTableEmptyNoCombination() {
        assertEquals("0,0", this.dao.mostPopularGame());
    }

    @Test
    public void whenTableEmptyNoRows() {
        assertEquals("no games", this.dao.mostPopularRow());
    }

    @Test
    public void whenTableEmptyNoSize() {
        assertEquals("no games", this.dao.mostPopularSize());
    }
    
    @Test
    public void whenTableEmptyNoCombinationQuantity() {
        assertEquals("0", this.dao.mostPopularPlayed());
    }
    
    @Test
    public void whenTableEmptyNoAverage() {
        assertEquals("0,0", this.dao.mostPopularGame());
    }
}
