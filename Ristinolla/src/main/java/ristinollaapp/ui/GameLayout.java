package ristinollaapp.ui;

import ristinollaapp.domain.Grid;

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
    private Grid gamegrid;
    private String vuoro;

    public GameLayout(int size) {
        this.gameLayout = new BorderPane();
        this.gamegrid = new Grid(size);
        this.vuoro = "X";

        createLayout();
    }

    public void createLayout() {
        Label turn = new Label("Vuoro: " + this.vuoro);
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
    
    public void setVuoro(){
        if (this.vuoro.equals("X")){
            this.vuoro = "0";
        } else{
            this.vuoro = "X";
        }
        
    }
            

}
