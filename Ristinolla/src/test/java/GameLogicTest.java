
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ristinollaapp.domain.GameLogic;
import ristinollaapp.ui.GridUi;
import ristinollaapp.ui.StartMenu;

public class GameLogicTest {

    GameLogic logic;

    public GameLogicTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        logic = new GameLogic(3, 0);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    @Test
    public void XHasFirstTurn() {
        assertEquals("X", logic.getTurn());
    }

    @Test
    public void SecondTurnIs0() {
        logic.changeTurn();
        assertEquals("0", logic.getTurn());
    }

    @Test
    public void scoreboardSetsXDuringTheFirstTurn() {
        logic.updateScore(0, 0);
        assertEquals("X", logic.getSymbolFromScoreBoard(0, 0));
    }

    @Test
    public void scoreboardSets0DuringTheSecondTurn() {
        logic.changeTurn();
        logic.updateScore(0, 0);
        assertEquals("0", logic.getSymbolFromScoreBoard(0, 0));
    }
    
    @Test
    public void GamesEndsWhenThereIsASymbolRow(){
        logic.updateScore(0, 0);
        logic.updateScore(0, 1);
        logic.updateScore(0, 2);
        assertEquals(true, logic.IsThereAWinnerInRows());
    }
    
    @Test
    public void GamesEndsWhenThereIsASymbolColumn(){
        logic.updateScore(0, 0);
        logic.updateScore(1, 0);
        logic.updateScore(2, 0);
        assertEquals(true, logic.IsThereAWinnerInColumns());
    }

    @Test
    public void hello() {
    }
}
