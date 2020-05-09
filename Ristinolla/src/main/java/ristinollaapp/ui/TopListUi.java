package ristinollaapp.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ristinollaapp.domain.TopListLogic;

/**
 * Creates a view for the top wins of a specific game.
 */
public class TopListUi {

    private BorderPane mainLayout;
    private BorderPane topListLayout;
    private TopListLogic topListLogic;
    private VBox topFiveLayout;

    /**
     * Creates a layout for the Toplist view.
     *
     * @param toplistlogic retrieves other top wins for a spesific game.
     * @param mainLayout mainlayout of the app, topListLayout will be set to it.
     */
    public TopListUi(BorderPane mainLayout, TopListLogic topListLogic) {
        this.topFiveLayout = new VBox(10);
        this.topListLogic = topListLogic;
        this.mainLayout = mainLayout;
        this.topListLayout = new BorderPane();

        createTopListLayout();
    }

    /**
     * Creates a layout for the Toplist view.
     *
     */
    public void createTopListLayout() {
        Insets insets = new Insets(20);
        Label titel = new Label("TOP-5 voittoa, joissa käytetty vähiten siirtoja");
        titel.setFont(new Font("Arial", 20));
        Label game = new Label(this.topListLogic.getgridSize() + "x" + this.topListLogic.getgridSize() + "-ruudukko, voittosuora " + this.topListLogic.getRowSize());
        game.setFont(new Font("Arial", 15));

        VBox titels = new VBox(10);

        titels.setAlignment(Pos.CENTER);
        titels.getChildren().addAll(titel, game);

        this.topFiveLayout = setTopFive();

        Button toStart = toStartMenu();

        this.topListLayout.setAlignment(toStart, Pos.TOP_CENTER);
        this.topListLayout.setMargin(toStart, insets);

        this.topListLayout.setTop(titels);
        this.topListLayout.setBottom(toStart);
        this.topListLayout.setCenter(this.topFiveLayout);
    }

    /**
     * Updates the view with the the method setTopFive().
     */
    public void updateList() {
        this.topFiveLayout.getChildren().removeAll(this.topFiveLayout.getChildren());

        setTopFive();
    }

    /**
     * Sets five top games to a VBox. Uses the class TopListLogic for that.
     */
    public VBox setTopFive() {
        ArrayList<String> list = this.topListLogic.topFive();
        Label titel = new Label("Nimimerkki:      siirrot:");
        titel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        this.topFiveLayout.getChildren().add(titel);
        for (int i = 0; i < list.size(); i++) {
            String[] nameMoves = list.get(i).split(",");
            this.topFiveLayout.getChildren().add(listEntry(i, nameMoves[0], nameMoves[1]));
        }

        this.topFiveLayout.setAlignment(Pos.CENTER);

        return this.topFiveLayout;
    }

    /**
     * Creates one entry with the proper alignments.
     *
     * @param rank the rank of the game
     * @param name name of the winner
     * @param moves amount moves it took to win the game
     */
    public BorderPane listEntry(int rank, String name, String moves) {
        BorderPane entryLayout = new BorderPane();
        entryLayout.setMaxWidth(125);
        Label rankName = new Label(rank + 1 + ".  " + name);
        Label movesLabel = new Label(moves);
        movesLabel.setAlignment(Pos.CENTER_LEFT);

        rankName.setAlignment(Pos.CENTER_LEFT);

        entryLayout.setLeft(rankName);
        entryLayout.setRight(movesLabel);

        return entryLayout;
    }

    /**
     * Creates a button to return to the start menu.
     */
    public Button toStartMenu() {
        Button toStart = new Button("Aloitusvalikkoon");

        toStart.setAlignment(Pos.CENTER);

        toStart.setOnAction((actionEvent -> {
            StartMenuUi startMenu = new StartMenuUi(this.mainLayout);
            this.mainLayout.setCenter(startMenu.getStartMenuLayout());
        }));

        return toStart;
    }

    public BorderPane getTopListLayout() {
        return this.topListLayout;
    }
}
