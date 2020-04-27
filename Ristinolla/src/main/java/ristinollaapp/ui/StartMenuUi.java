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
import ristinollaapp.domain.TopListLogic;

public class StartMenuUi {

    private BorderPane mainLayout;
    private BorderPane startMenu;
    private TextField gridSize;
    private TextField rowSize;
    private Label warning;

    /**
     * Constructor creates a new layout for the menu. It uses the method
     * createLayout()
     *
     * @param mainLayout mainLayout of the app, startMenulayout will be set to
     * it
     *
     */
    public StartMenuUi(BorderPane mainLayout) {
        this.mainLayout = mainLayout;
        this.startMenu = new BorderPane();
        this.warning = new Label("");

        createLayout();
    }

    /**
     * Creates a new layout for the start menu.
     *
     */
    public void createLayout() {

        startMenu.setPrefSize(500, 500);
        Insets insets = new Insets(20);
        BorderPane top = new BorderPane();

        Label titel = new Label("Tervetuloa pelaamaan ristinollaa!");
        titel.setFont(new Font("Arial", 20));
        titel.setAlignment(Pos.CENTER);

        top.setTop(statsButton());
        top.setCenter(titel);
        top.setMargin(titel, insets);

        VBox textFieldLayout = new VBox(8);
        Label instructions = new Label("Kirjoita ruudukon koko ja voittosuoran pituus. \n            Ruudukon koon tulee olla 3–7 \n    Voittosuora ei voi olla ruudukkoa isompi!");
        this.gridSize = new TextField("ruudukko");
        gridSize.setMaxWidth(80);
        this.rowSize = new TextField("suora");
        rowSize.setMaxWidth(80);
        textFieldLayout.setAlignment(Pos.CENTER);
        this.warning.setAlignment(Pos.CENTER);
        textFieldLayout.getChildren().addAll(instructions, gridSize, rowSize, warning);

        Button chooseButton = createButton();

        startMenu.setAlignment(textFieldLayout, Pos.CENTER);
        startMenu.setAlignment(top, Pos.CENTER);
        startMenu.setAlignment(chooseButton, Pos.TOP_CENTER);
        startMenu.setMargin(chooseButton, insets);

        startMenu.setTop(top);
        startMenu.setCenter(textFieldLayout);
        startMenu.setBottom(chooseButton);

        mainLayout.setCenter(startMenu);

    }

    /**
     * Creates a button that collects the values the user gave in the
     * TextFields. The method validates the data given by the user. This means
     * gridsize can't be smaller than the rowsize and all values must be
     * Integer.
     *
     * @return Button object that moves to user to the game
     *
     */
    public Button createButton() {
        Button sizesChosen = new Button("Valitse ja pelaa");

        sizesChosen.setOnAction((actionEvent -> {
            try {
                if (grid() > 2 && grid() >= row() && row() > 2 && grid() < 8) {
                    GameLayoutUi gameLayout;
                    gameLayout = new GameLayoutUi(grid(), row(), mainLayout);
                    mainLayout.setCenter(gameLayout.getLayout());

                } else {
                    this.warning.setText("Tarkista, että ruudukko ja voittosuora ovat oikean kokoisia!");
                }
            } catch (NumberFormatException e) {
                this.warning.setText("Senkin veijari! Numeroita, ei tekstiä!");
            }
        }));

        return sizesChosen;
    }

    /**
     * Creates a button that retrieves data from the TopLists table and
     * creates a new layout for the statistics.
     *
     * @return Button object that moves to user to the stats
     *
     */
    public Button statsButton() {
        Button toStats = new Button("Pelien tilastoihin");

        toStats.setOnAction((actionEvent -> {
            StatsUi statsLayout = new StatsUi(new TopListLogic("toplists.db"), mainLayout);
            mainLayout.setCenter(statsLayout.getLayout());

        }));

        return toStats;
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
