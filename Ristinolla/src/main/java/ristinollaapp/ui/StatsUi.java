package ristinollaapp.ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ristinollaapp.domain.TopListLogic;

/**
 * Creates a view with statistics from previously played games.
 *
 */
public class StatsUi {

    private BorderPane mainLayout;
    private BorderPane statsLayout;
    private TopListLogic topListLogic;

    /**
     * Creates a new layout for the statistics.
     *
     * @param toplistlogic retrieves information about the previous games
     * @param mainLayout mainlayout of the app, statsLayout will be set to it
     *
     */
    public StatsUi(TopListLogic topListLogic, BorderPane mainLayout) {
        this.mainLayout = mainLayout;
        this.topListLogic = topListLogic;
        this.statsLayout = new BorderPane();

        create();
    }

    /**
     * Creates a new layout for the statistics.
     *
     */
    public void create() {
        Insets insets = new Insets(20);
        Label titel = new Label("Pelien tilastoja");
        titel.setFont(new Font("Arial", 20));

        Label size = new Label("Suosituin ruudukon koko: " + this.topListLogic.mostPopularSize());

        Button toStart = toStartMenu();

        this.statsLayout.setAlignment(titel, Pos.CENTER);
        this.statsLayout.setAlignment(toStart, Pos.CENTER);
        this.statsLayout.setMargin(titel, insets);
        this.statsLayout.setMargin(toStart, insets);

        this.statsLayout.setTop(titel);
        this.statsLayout.setCenter(stats());
        this.statsLayout.setBottom(toStart);
    }

    /**
     * Retrieves information about previous games using the topListLogic class
     * and places them to a VBox.
     *
     */
    public VBox stats() {
        VBox stats = new VBox(5);

        BorderPane size;
        BorderPane row;
        BorderPane overall;

        if (this.topListLogic.games().equals("0")) {
            size = listEntry("Suosituin ruudukko", "-");
            row = listEntry("Suosituin voittosuora", "-");
            overall = listEntry("Suosituin ruudukko–\nvoittoriviyhdistelmä", "-");

        } else {
            size = listEntry("Suosituin ruudukko", this.topListLogic.mostPopularSize() + "x" + this.topListLogic.mostPopularSize());
            row = listEntry("Suosituin voittosuora", this.topListLogic.mostPopularRow());
            overall = listEntry("Suosituin ruudukko–\nvoittosuorayhdistelmä", this.topListLogic.mostPopularCombSize() + "x" + this.topListLogic.mostPopularCombSize() + " ja " + this.topListLogic.mostPopularCombRow());
        }

        BorderPane sizeGames = listEntry("Pelejä", this.topListLogic.popularSizeQuantity());
        BorderPane rowGames = listEntry("Pelejä", this.topListLogic.popularRowQuantity());
        BorderPane overallGames = listEntry("Pelejä", this.topListLogic.popularPlayed());
        BorderPane games = listEntry("Kaikkia pelejä pelattu", this.topListLogic.games());
        BorderPane averagewin = listEntry("Voittoon tarvittavien \nsiirtojen keskiarvo", this.topListLogic.averageMoves());

        stats.getChildren().addAll(averagewin, empty(), games, empty(), overall, overallGames, empty(), row, rowGames, empty(), size, sizeGames);
        stats.setAlignment(Pos.CENTER);

        return stats;
    }

    /**
     * Creates an empty entry to spaceout all the entries.
     *
     */
    public BorderPane empty() {
        return listEntry("", "");
    }

    /**
     * Creates one entry with the proper alignments.
     *
     * @param text text for the wanted entry
     * @param palyed how many times the game has been played
     */
    public BorderPane listEntry(String text, String played) {
        BorderPane entryLayout = new BorderPane();
        entryLayout.setMaxWidth(250);
        Label rankName = new Label(text);
        Label movesLabel = new Label(played);
        movesLabel.setAlignment(Pos.CENTER_LEFT);

        rankName.setAlignment(Pos.CENTER_LEFT);

        entryLayout.setLeft(rankName);
        entryLayout.setRight(movesLabel);

        return entryLayout;
    }

     /**
     * Creates a button for going back to the start menu.
     *
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

    public BorderPane getLayout() {
        return this.statsLayout;
    }

}
