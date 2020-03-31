package ristinollaapp.domain;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import ristinollaapp.ui.GameLayout;

public class Grid {

    private GridPane grid;
    private String vuoro;

    public Grid(int size){
            this.vuoro = "X";

            this.grid = new GridPane();

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    Button button = createButton();
                    grid.add(button, i, j);
                }
            }

            this.grid.setAlignment(Pos.CENTER);

    }

    public Button createButton() {
        Button button = new Button(" ");
        button.setFont(Font.font("Monospaced", 25));

        button.setOnAction((actionEvent) -> {
//            setVuoro();
//            System.out.println("Täällä");
        });

        return button;
    }

    public GridPane getGameGrid() {
        return grid;
    }

    public String getVuoro() {
        return this.vuoro;
    }

    public void setVuoro() {
        if (this.vuoro.equals("X")) {
            this.vuoro = "0";
        } else {
            this.vuoro = "X";
        }
    }

}
