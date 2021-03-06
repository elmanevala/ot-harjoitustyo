package ristinollaapp.ui;

import java.sql.SQLException;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Creates a layout for the game and passes on the user-given data to other
 * classes.
 *
 */
public class GameLayoutUi {

    private BorderPane gameLayout;
    private GridUi gamegrid;
    private Label turn;
    private BorderPane mainLayout;
    private int row;

    /**
     * Constructor creates a new layout for the game. It uses the method
     * create()
     *
     * @param size size of the game grid
     * @param row how long the winning grid must be
     * @param mainLayout mainlayout of the app, gamelayout will be set to it
     *
     */
    public GameLayoutUi(int size, int row, BorderPane mainLayout) {
        this.row = row;
        this.mainLayout = mainLayout;
        this.gameLayout = new BorderPane();
        this.gamegrid = new GridUi(size, row, this, mainLayout);
        this.turn = new Label("Vuoro: " + this.gamegrid.getTurn());

        createLayout();
    }

    /**
     * Creates a new layout for the game.
     */
    public void createLayout() {
        turn.setFont(new Font("Arial", 20));
        Label rowText = new Label("Voittosuoran pituus on " + this.row);
        rowText.setFont(new Font("Arial", 15));

        VBox titels = new VBox(10);
        titels.setAlignment(Pos.CENTER);
        titels.getChildren().addAll(this.turn, rowText);

        gameLayout.setAlignment(titels, Pos.CENTER);
        gameLayout.setTop(titels);

        GridPane grid = gamegrid.getGameGrid();

        gameLayout.setAlignment(grid, Pos.CENTER);
        gameLayout.setCenter(grid);

    }

    public BorderPane getLayout() {
        return gameLayout;
    }

    /**
     * Changes the titel of the layout according to the turn from the GameLogic
     * class.
     *
     * @param turn which turn is it
     */
    public void setTurn(String turn) {
        this.turn.setText("Vuoro: " + this.gamegrid.getTurn());
    }

}
