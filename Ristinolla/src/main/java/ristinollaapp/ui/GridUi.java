package ristinollaapp.ui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import ristinollaapp.domain.GameLogic;
import ristinollaapp.ui.GameLayoutUi;

/**
 * Creates grid for the game and with the GameLogic class
 * executes turns in the game.
 *
 */
public class GridUi {

    private GridPane grid;
    private GameLogic gamelogic;
    private int size;
    private GameLayoutUi layout;
    private BorderPane mainLayout;

    /**
     * Constructor creates a new Gamegrid for the game.
     *
     * @param size size of the grid
     * @param layout the layout the grid will be set to
     * @param mainLayout mainLayout of the app, layout will be set to it
     *
     */
    public GridUi(int size, int row, GameLayoutUi layout, BorderPane mainLayout) {
        this.mainLayout = mainLayout;
        this.layout = layout;
        this.size = size;
        this.gamelogic = new GameLogic(size, row);
        this.grid = new GridPane();

        creatingGrid();
    }

           /**
     * Creates a new grid according to the size-object
     */
    public void creatingGrid() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                Button button = createButton(i, j);
                grid.add(button, j, i);
            }
        }

        this.grid.setAlignment(Pos.CENTER);
    }

    /**
     * Communicates with the GameLogic class to execute one turn.
     * 
     * Creates a new button. When the button is pushed, method checks if the place
     * given in the parameters is empty and sets a new value to the button. 
     * If the game ends and there is a winner, new layout is created and user must submit
     * their name to the database.
     * If the game ends in a draw, new game is added without a name.
     *
     * @param x horizontal location
     * @param y vertical location
     *
     */
    public Button createButton(int x, int y) {
        Button button = new Button(" ");
        button.setFont(Font.font("Monospaced", 25));

        button.setOnAction((actionEvent) -> {
            if (this.gamelogic.spaceEmpty(x, y)) {
                this.gamelogic.updateScore(x, y);
                button.setText(this.gamelogic.getTurn());
                String turn = this.gamelogic.changeTurn();
                this.layout.setTurn(turn);

                if (this.gamelogic.isThereAWinner(x, y)) {
                    WinnerLayoutUi winnerLayout = new WinnerLayoutUi(this.gamelogic, this.mainLayout, false);
                    this.mainLayout.setCenter(winnerLayout.getLayout());

                } else if (this.gamelogic.scoreboardFull()) {
                    WinnerLayoutUi winnerLayout = new WinnerLayoutUi(this.gamelogic, this.mainLayout, true);
                    this.mainLayout.setCenter(winnerLayout.getLayout());

                }
                this.gamelogic.printScoreBoard(); // Tarkistetaan muuttuko pelitilanne pelilogiikassa
            }
        });

        return button;
    }

    public GridPane getGameGrid() {
        return grid;
    }

    public String getTurn() {
        return this.gamelogic.getTurn();
    }

}
