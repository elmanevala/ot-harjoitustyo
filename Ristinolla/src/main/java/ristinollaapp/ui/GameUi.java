package ristinollaapp.ui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The class that starts the game.
 */
public class GameUi extends Application {

    BorderPane mainLayout;

    /**
     * Launches the game.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Sets the stage for the game and creates a new start menu.
     */
    @Override
    public void start(Stage stage) {

        stage.setTitle("Ristinolla");

        this.mainLayout = new BorderPane();

        StartMenuUi startmenu = new StartMenuUi(this.mainLayout);
        BorderPane layout = startmenu.getMainLayout();

        Scene nakyma = new Scene(layout);
        stage.setScene(nakyma);
        stage.show();

    }

}
