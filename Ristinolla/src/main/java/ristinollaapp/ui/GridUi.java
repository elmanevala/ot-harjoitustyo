package ristinollaapp.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import ristinollaapp.domain.GameLogic;
import ristinollaapp.ui.GameLayout;

public class GridUi {

    private GridPane grid;
    private GameLogic gamelogic;
    private int size;
    private GameLayout layout;
    private BorderPane mainLayout;

    public GridUi(int size, int row, GameLayout layout, BorderPane mainLayout) {
        this.mainLayout = mainLayout;
        this.layout = layout;
        this.size = size;
        this.gamelogic = new GameLogic(size, row);
        this.grid = new GridPane();
        

        creatingGrid();
    }

    public void creatingGrid() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                Button button = createButton(i, j);
                grid.add(button, j, i);
            }
        }

        this.grid.setAlignment(Pos.CENTER);
    }

    public Button createButton(int x, int y) {
        Button button = new Button(" ");
        button.setFont(Font.font("Monospaced", 25));

        button.setOnAction((actionEvent) -> {
            if (this.gamelogic.spaceEmpty(x, y)) {
                this.gamelogic.updateScore(x, y);
                button.setText(this.gamelogic.getTurn());
                this.gamelogic.changeTurn();
                this.layout.setTurn(this.gamelogic.getTurn());

                if (this.gamelogic.isThereAWinner(x,y)) {
                    WinnerLayoutUi winnerLayout = new WinnerLayoutUi(this.gamelogic);
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
