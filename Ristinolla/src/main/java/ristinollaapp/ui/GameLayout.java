package ristinollaapp.ui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameLayout {

    private BorderPane gameLayout;
    private GridUi gamegrid;
    private Label turn;
    private BorderPane mainLayout;

    public GameLayout(int size, BorderPane mainLayout) {
        this.mainLayout = mainLayout;
        this.gameLayout = new BorderPane();
        this.gamegrid = new GridUi(size, this, mainLayout);
        this.turn = new Label("Vuoro: " + this.gamegrid.getVuoro());

        createLayout();
    }

    public void createLayout() {
        turn.setFont(new Font("Arial", 20));

        gameLayout.setAlignment(turn, Pos.CENTER);
        gameLayout.setTop(turn);

        GridPane grid = gamegrid.getGameGrid();

        gameLayout.setAlignment(grid, Pos.CENTER);
        gameLayout.setCenter(grid);

    }

    public BorderPane getLayout() {
        return gameLayout;
    }

    public void setTurn(String turn) {
        this.turn.setText("Vuoro: " + this.gamegrid.getVuoro());
    }

}
