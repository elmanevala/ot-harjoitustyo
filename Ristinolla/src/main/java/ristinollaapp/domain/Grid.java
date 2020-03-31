package ristinollaapp.domain;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import ristinollaapp.ui.GameLayout;

public class Grid {

    private GridPane grid;

    public Grid(int size) {
        
        this.grid = new GridPane();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Button button = createButton();
                grid.add(button, i, j);
            }
        }

    }

    public Button createButton() {
        Button button = new Button(" ");
        button.setFont(Font.font("Monospaced", 25));

        button.setOnAction((actionEvent -> {
            // pelitoiminnallisuutta ei vielä ole.
        }));

        return button;
    }
    
    public GridPane getGameGrid(){
        return grid;
    }

}
