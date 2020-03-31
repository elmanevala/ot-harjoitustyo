
package ristinollaapp.ui;

import ristinollaapp.domain.Grid;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
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
    
    private HBox gameLayout;
    private Grid gamegrid;
    
    public GameLayout(int size){
        this.gameLayout = new HBox();
        this.gamegrid = new Grid(size);
    }
    
    public HBox getLayout(){
        return gameLayout;
    }
    
    
}
