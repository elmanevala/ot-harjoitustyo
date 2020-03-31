package ristinollaapp.ui;

import java.util.ArrayList;
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

public class StartMenu {

    private BorderPane mainLayout;
    private BorderPane startMenu;

    public StartMenu() {
        this.mainLayout = new BorderPane();
        this.startMenu = new BorderPane();

        createLayout();
    }

    public void createLayout() {

        startMenu.setPrefSize(400, 400);
        Insets insets = new Insets(20);

        Label titel = new Label("Tervetuloa pelaamaan ristinollaa!");
        titel.setFont(new Font("Arial", 20));

        VBox textFieldLayout = new VBox(8);
        Label instructions = new Label("Valitse ruudukon koko ja voittorivin pituus: ");
        TextField gridSize = new TextField("Ruudukon koko (3–7)");
        gridSize.setMaxWidth(100);
        TextField rowSize = new TextField("Voittorivin pituus");
        rowSize.setMaxWidth(100);
        textFieldLayout.setAlignment(Pos.CENTER);
        textFieldLayout.getChildren().addAll(instructions, gridSize, rowSize);

        Button chooseButton = createButton(gridSize);

        startMenu.setAlignment(textFieldLayout, Pos.CENTER);
        startMenu.setAlignment(titel, Pos.CENTER);
        startMenu.setAlignment(chooseButton, Pos.TOP_CENTER);
        startMenu.setMargin(chooseButton, insets);

        startMenu.setTop(titel);
        startMenu.setCenter(textFieldLayout);
        startMenu.setBottom(chooseButton);

        mainLayout.setCenter(startMenu);

    }

    public Button createButton(TextField text) {
        Button sizesChosen = new Button("Valitse ja pelaa");

        if (!text.getText().equals("")) {
            sizesChosen.setOnAction((actionEvent -> {
                GameLayout gameLayout = new GameLayout(Integer.valueOf(text.getText()));
                mainLayout.setCenter(gameLayout.getLayout());
            }));
        }

        return sizesChosen;
    }

    public BorderPane getStartMenuLayout() {
        return mainLayout;
    }

}
