
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ristinollaapp.ui.GridUi;
import ristinollaapp.ui.StartMenu;

public class GridTest {

    public GridTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    
    @Test
    public void XHasFirstTurn(){
        GridUi grid = new GridUi(3);
        
        assertEquals("X", grid.getVuoro());
    }
    
    

    @Test
    public void hello() {
    }
}
