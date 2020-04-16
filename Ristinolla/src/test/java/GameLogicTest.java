
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
        logic = new GameLogic(3, 3);

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
    public void GamesEndsWhenThereIsASymbolRow() {
        logic.updateScore(0, 0);
        logic.updateScore(0, 1);
        logic.updateScore(0, 2);
        assertEquals(true, logic.isThereAWinnerInRows(0, 2));
    }

    @Test
    public void GamesEndsWhenThereIsASymbolColumn() {
        logic.updateScore(0, 0);
        logic.updateScore(1, 0);
        logic.updateScore(2, 0);
        assertEquals(true, logic.isThereAWinnerInColumns(2, 0));
    }

    @Test
    public void GameWontEndEarlyCol() {
        logic.updateScore(0, 0);
        logic.updateScore(1, 2);
        logic.updateScore(2, 0);
        assertEquals(false, logic.isThereAWinnerInColumns(2, 0));
    }

    @Test
    public void GameWontEndEarlyRow() {
        logic.updateScore(0, 0);
        logic.updateScore(1, 2);
        logic.updateScore(0, 1);
        assertEquals(false, logic.isThereAWinnerInRows(0, 1));
    }

    @Test
    public void GameEndsDiagLeftUp() {
        logic.updateScore(0, 2);
        logic.updateScore(1, 1);
        logic.updateScore(2, 0);
        assertEquals(true, logic.winnerDiagLeftUp(1, 1));
    }

    @Test
    public void GameEndsDiagRightDown() {
        logic.updateScore(0, 0);
        logic.updateScore(1, 1);
        logic.updateScore(2, 2);
        assertEquals(true, logic.winnerDiagRightDown(1, 1));
    }

    @Test
    public void GameWontEndDiagRightDown() {
        logic.updateScore(0, 0);
        logic.updateScore(2, 1);
        logic.updateScore(2, 2);
        assertEquals(false, logic.winnerDiagRightDown(1, 1));
    }

    @Test
    public void GameWontEndDiagLeftUp() {
        logic.updateScore(0, 0);
        logic.updateScore(2, 1);
        logic.updateScore(2, 2);
        assertEquals(false, logic.winnerDiagLeftUp(1, 1));
    }

    public void winnerRowisSetCorrect() {
        assertEquals(3, logic.getRowSize());
    }

    @Test
    public void winnerFoundFromColumns() {
        logic.updateScore(0, 0);
        logic.updateScore(1, 0);
        logic.updateScore(2, 0);
        assertEquals(true, logic.isThereAWinner(1, 0));
    }

    @Test
    public void winnerFoundFromRows() {
        logic.updateScore(0, 0);
        logic.updateScore(0, 1);
        logic.updateScore(0, 2);
        assertEquals(true, logic.isThereAWinner(0, 0));
    }

    @Test
    public void winnerFoundDiagRightDown() {
        logic.updateScore(0, 0);
        logic.updateScore(1, 1);
        logic.updateScore(2, 2);
        assertEquals(true, logic.isThereAWinner(0, 0));
    }

    @Test
    public void winnerFoundDiagLeftUp() {
        logic.updateScore(0, 2);
        logic.updateScore(1, 1);
        logic.updateScore(2, 0);
        assertEquals(true, logic.isThereAWinner(0, 2));
    }
    
    
    @Test
    public void winnerNotFoundWhenNoRows() {
        logic.updateScore(1, 0);
        logic.updateScore(1, 1);
        logic.updateScore(2, 0);
        assertEquals(false, logic.isThereAWinner(1, 1));
    }
    
    @Test
    public void emptyWhenNoUpdates() {
        assertEquals(true, logic.spaceEmpty(0, 0));
    }
    
       @Test
    public void spaceFullWhenUpdated() {
        logic.updateScore(0, 0);
        assertEquals(false, logic.spaceEmpty(0, 0));
    }

    @Test
    public void hello() {
    }
}
