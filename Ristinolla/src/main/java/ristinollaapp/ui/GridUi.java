package ristinollaapp.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import ristinollaapp.domain.GameLogic;
import ristinollaapp.ui.GameLayout;

public class GridUi {

    private GridPane grid;
    private GameLogic gamelogic;
    private int size;

    public GridUi(int size) {
        this.size = size;
        this.gamelogic = new GameLogic(size, 0);
        this.grid = new GridPane();
        
        creatingGrid();
    }

    public void creatingGrid() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                Button button = createButton(i, j);
                grid.add(button, i, j);
            }
        }

        this.grid.setAlignment(Pos.CENTER);
    }

    public Button createButton(int x, int y) {
        Button button = new Button(" ");
        button.setFont(Font.font("Monospaced", 25));

        button.setOnAction((actionEvent) -> {
            this.gamelogic.changeTurn();
            this.gamelogic.updateScore(x, y);
            
            button.setText(this.gamelogic.getTurn());
            
            this.gamelogic.printScoreBoard(); // Tarkistetaan muuttuko pelitilanne pelilogiikassa
            System.out.println(this.gamelogic.getTurn()); // Tarkistetaan vaihtaako painaminen vuoroa
        });

        return button;
    }

    public GridPane getGameGrid() {
        return grid;
    }

    public String getVuoro() {
        return this.gamelogic.getTurn();
    }

}
