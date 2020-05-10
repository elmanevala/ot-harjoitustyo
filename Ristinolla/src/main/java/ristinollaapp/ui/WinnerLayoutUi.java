package ristinollaapp.ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ristinollaapp.domain.GameLogic;
import ristinollaapp.domain.TopListLogic;

/**
 * Creates a view for a game that has ended.
 */
public class WinnerLayoutUi {

    private BorderPane winnerLayout;
    private String winner;
    private GameLogic gameLogic;
    private TopListLogic topListLogic;
    private BorderPane mainLayout;
    private boolean draw;
    private Label warning;

    /**
     * Creates a layout for the game ending view.
     *
     * @param gamelogic retrieves information from the game that ended
     * @param mainLayout mainlayout of the app, winnerLayout will be set to it.
     */
    public WinnerLayoutUi(GameLogic gameLogic, BorderPane mainLayout, boolean draw) {
        this.warning = new Label("");
        this.draw = draw;
        this.mainLayout = mainLayout;
        this.gameLogic = gameLogic;
        this.winnerLayout = new BorderPane();
        this.winner = this.gameLogic.getWinner();
        this.topListLogic = new TopListLogic(gameLogic.getGridSize(), gameLogic.getRowSize(), gameLogic.getWinnerMoves(), dbname());

        createLayout();
    }

    /**
     * Creates a the game ending layout.
     */
    public void createLayout() {
        Label winner = new Label();
        if (this.draw) {
            winner.setText("Peli päättyi tasapeliin");
        } else {
            winner.setText("Onneksi olkoon, " + this.winner + " voitit!");
        }
        Insets insets = new Insets(100);
        winnerLayout.setAlignment(winner, Pos.CENTER);

        VBox buttons = new VBox(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(insets);

        if (topListLogic.isInTopFive() && !this.draw) {
            TextField winnerName = nameField();
            buttons.getChildren().addAll(new Text("Mahtavaa, pääsit TOP-listalle!"), winnerName, addTopListButton(winnerName), this.warning);
        } else {
            buttons.getChildren().addAll(addStartMenuButton(), toTopListButton());
            this.topListLogic.addName("");
            System.out.println(this.topListLogic.mostPopularSize());
        }

        winnerLayout.setMargin(winner, new Insets(50));
        winnerLayout.setTop(winner);
        winnerLayout.setCenter(buttons);
    }

    /**
     * Creates a button to return to the start menu.
     */
    public Button addStartMenuButton() {
        Button toStart = new Button("Aloitusvalikkoon");

        toStart.setAlignment(Pos.CENTER);

        toStart.setOnAction((actionEvent -> {
            StartMenuUi startMenu = new StartMenuUi(mainLayout);
            mainLayout.setCenter(startMenu.getStartMenuLayout());
        }));

        return toStart;
    }

    /**
     * Creates a button to go to the toplist view. Adds a game to the database
     * and validates the information the user gave in the text field.
     */
    public Button addTopListButton(TextField name) {
        Button toLists = new Button("Lisää nimimerkkisi!");

        toLists.setAlignment(Pos.CENTER);

        toLists.setOnAction((actionEvent -> {
            if (!name.getText().equals("")) {
                TopListUi topList;

                topList = new TopListUi(mainLayout, this.topListLogic);
                this.topListLogic.addName(name.getText());
                topList.updateList();
                mainLayout.setCenter(topList.getTopListLayout());
            } else {
                this.warning.setText("Nimimerkki ei voi olla tyhjä!");
            }

        }));

        return toLists;
    }

    /**
     * Creates a button to view the top wins for this game.
     */
    public Button toTopListButton() {
        Button toLists = new Button("TOP-listat");

        toLists.setAlignment(Pos.CENTER);

        toLists.setOnAction((actionEvent -> {
            TopListUi topList;
            topList = new TopListUi(mainLayout, this.topListLogic);
            topList.updateList();
            mainLayout.setCenter(topList.getTopListLayout());

        }));

        return toLists;
    }

    /**
     * Creates a text field for the winner that gets to go to the top list.
     */
    public TextField nameField() {
        TextField winnerName = new TextField();
        winnerName.setPromptText("nimimerkki");
        winnerName.setAlignment(Pos.CENTER);
        winnerName.setMaxWidth(120);

        return winnerName;
    }

    public BorderPane getLayout() {
        return this.winnerLayout;
    }

    /**
     * Retrieves the name of the file to which the data from the games will be
     * stored.
     *
     * @return name of the file as a String.
     */
    public String dbname() {
        try {
            Properties properties = new Properties();

            properties.load(new FileInputStream("config.properties"));

            String topListFile = properties.getProperty("topListsFile");

            return topListFile;
        } catch (IOException e) {
            System.out.println("Tiedostoa ei löydy!");
            return null;
        }

    }

}
