package ristinollaapp.ui;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.sqlite.SQLiteException;

public class StartMenuUi {

    private BorderPane mainLayout;
    private BorderPane startMenu;
    private TextField gridSize;
    private TextField rowSize;
    private Label warning;

    public StartMenuUi(BorderPane mainLayout) {
        this.mainLayout = mainLayout;
        this.startMenu = new BorderPane();
        this.warning = new Label("");

        createLayout();
    }

    public void createLayout() {

        startMenu.setPrefSize(500, 500);
        Insets insets = new Insets(20);

        Label titel = new Label("Tervetuloa pelaamaan ristinollaa!");
        titel.setFont(new Font("Arial", 20));

        VBox textFieldLayout = new VBox(8);
        Label instructions = new Label("Kirjoita ruudukon koko ja voittosuoran pituus. \n        Molempien minimipituus on kolme. \n    Voittosuora ei voi olla ruudukkoa isompi!");
        this.gridSize = new TextField("ruudukko");
        gridSize.setMaxWidth(70);
        this.rowSize = new TextField("suora");
        rowSize.setMaxWidth(70);
        textFieldLayout.setAlignment(Pos.CENTER);
        this.warning.setAlignment(Pos.CENTER);
        textFieldLayout.getChildren().addAll(instructions, gridSize, rowSize, warning);

        Button chooseButton = createButton();

        startMenu.setAlignment(textFieldLayout, Pos.CENTER);
        startMenu.setAlignment(titel, Pos.CENTER);
        startMenu.setAlignment(chooseButton, Pos.TOP_CENTER);
        startMenu.setMargin(chooseButton, insets);

        startMenu.setTop(titel);
        startMenu.setCenter(textFieldLayout);
        startMenu.setBottom(chooseButton);

        mainLayout.setCenter(startMenu);

    }

    public Button createButton() {
        Button sizesChosen = new Button("Valitse ja pelaa");

        sizesChosen.setOnAction((actionEvent -> {
            if (grid() > 2 && grid() >= row() && row() > 2) {
                GameLayoutUi gameLayout;
                try {
                    gameLayout = new GameLayoutUi(grid(), row(), mainLayout);
                    mainLayout.setCenter(gameLayout.getLayout());
                } catch (SQLException ex) {
                    Logger.getLogger(StartMenuUi.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                this.warning.setText("Tarkista, että ruudukko ja voittosuora, ovat oikean kokoisia!");
            }
        }));

        return sizesChosen;
    }

    public int grid() {
        return Integer.valueOf(this.gridSize.getText());
    }

    public int row() {
        return Integer.valueOf(this.rowSize.getText());
    }

    public BorderPane getMainLayout() {
        return mainLayout;
    }

    public BorderPane getStartMenuLayout() {
        return this.startMenu;
    }

}
