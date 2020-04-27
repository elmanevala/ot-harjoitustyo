package ristinollaapp.ui;

import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ristinollaapp.domain.TopListLogic;

public class StatsUi {

    private BorderPane mainLayout;
    private BorderPane statsLayout;
    private TopListLogic topListLogic;

    public StatsUi(TopListLogic topListLogic, BorderPane mainLayout) {
        this.mainLayout = mainLayout;
        this.topListLogic = topListLogic;
        this.statsLayout = new BorderPane();

        create();
    }

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

    public VBox stats() {
        VBox stats = new VBox(5);

        BorderPane size = listEntry("Suosituin ruudukko", this.topListLogic.mostPopularSize() + "x" + this.topListLogic.mostPopularSize());
        BorderPane sizeGames = listEntry("Pelejä", this.topListLogic.popularSizeQuantity());
        BorderPane row = listEntry("Suosituin voittorivi", this.topListLogic.mostPopularRow());
        BorderPane rowGames = listEntry("Pelejä", this.topListLogic.popularRowQuantity());
        BorderPane overall = listEntry("Suosituin\nruudukko–voittoriviyhdistelmä", this.topListLogic.mostPopularCombSize() + "x" + this.topListLogic.mostPopularCombSize() + " ja " + this.topListLogic.mostPopularCombRow());
        BorderPane overallGames = listEntry("Pelejä", this.topListLogic.popularPlayed());
        BorderPane games = listEntry("Kaikkia pelejä pelattu", this.topListLogic.games());
        BorderPane averagewin = listEntry("Voittoon tarvittavien \nsiirtojen keskiarvo", this.topListLogic.averageMoves());

        stats.getChildren().addAll(averagewin, empty(), games, empty(), overall, overallGames, empty(), row, rowGames, empty(), size, sizeGames);
        stats.setAlignment(Pos.CENTER);

        return stats;
    }
    
    public BorderPane empty(){
        return listEntry("","");
    }

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
