package ristinollaapp.ui;

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

    public StartMenuUi(BorderPane mainLayout) {
        this.mainLayout = mainLayout;
        this.startMenu = new BorderPane();

        createLayout();
    }

    public void createLayout() {

        startMenu.setPrefSize(500, 500);
        Insets insets = new Insets(20);

        Label titel = new Label("Tervetuloa pelaamaan ristinollaa!");
        titel.setFont(new Font("Arial", 20));

        VBox textFieldLayout = new VBox(8);
        Label instructions = new Label("Kirjoita ruudukon koko ja voittorivin pituus. \n   Voittorivi ei voi olla ruudukkoa isompi!");
        TextField gridSize = new TextField("koko (3–7)");
        gridSize.setMaxWidth(115);
        TextField rowSize = new TextField("pituus (3–MAX)");
        rowSize.setMaxWidth(115);
        textFieldLayout.setAlignment(Pos.CENTER);
        textFieldLayout.getChildren().addAll(instructions, gridSize, rowSize);

        Button chooseButton = createButton(gridSize, rowSize);

        startMenu.setAlignment(textFieldLayout, Pos.CENTER);
        startMenu.setAlignment(titel, Pos.CENTER);
        startMenu.setAlignment(chooseButton, Pos.TOP_CENTER);
        startMenu.setMargin(chooseButton, insets);

        startMenu.setTop(titel);
        startMenu.setCenter(textFieldLayout);
        startMenu.setBottom(chooseButton);

        mainLayout.setCenter(startMenu);

    }

    public Button createButton(TextField grid, TextField row) {
        Button sizesChosen = new Button("Valitse ja pelaa");

        if (!grid.getText().equals("")) {
            sizesChosen.setOnAction((actionEvent -> {
                GameLayoutUi gameLayout;
                try {
                    gameLayout = new GameLayoutUi(Integer.valueOf(grid.getText()), Integer.valueOf(row.getText()), mainLayout);
                    mainLayout.setCenter(gameLayout.getLayout());
                } catch (SQLException ex) {
                    Logger.getLogger(StartMenuUi.class.getName()).log(Level.SEVERE, null, ex);
                }

            }));
        }

        return sizesChosen;
    }

    public BorderPane getMainLayout() {
        return mainLayout;
    }

    public BorderPane getStartMenuLayout() {
        return this.startMenu;
    }

}
