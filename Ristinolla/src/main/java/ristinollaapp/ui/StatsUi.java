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

    public StatsUi(TopListLogic topListLogic, BorderPane mainLayout) throws SQLException {
        this.mainLayout = mainLayout;
        this.topListLogic = topListLogic;
        this.statsLayout = new BorderPane();

        create();
    }

    public void create() throws SQLException {
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

    public VBox stats() throws SQLException {
        VBox stats = new VBox(10);

        String[] sizeAndQuantity = this.topListLogic.mostPopularSize().split(",");
        String[] rowAndQuantity = this.topListLogic.mostPopularRow().split(",");
        String[] sizeAndRow = this.topListLogic.mostPopularCombination().split(",");

        Label size = new Label("Suosituin ruukko: " + sizeAndQuantity[0] + "x" + sizeAndQuantity[0] + "\nPelejä: " + sizeAndQuantity[1]);
        Label row = new Label("Suosituin voittosuora: " + rowAndQuantity[0] + "\nPelejä: " + rowAndQuantity[1]);
        Label overall = new Label("Suosituin peliyhdistelmä: " + "\n" + sizeAndRow[0] + "x" + sizeAndRow[0] + "-ruudukko ja voittosuora " + sizeAndRow[1] + "\nPelejä: " + this.topListLogic.popularPlayed());
        Label games = new Label("Pelejä pelattu yhteensä: " + this.topListLogic.games());
        Label averagewin = new Label("Voittoon tarvittuja siirtoja keskimäärin: " + "\n" + this.topListLogic.averageMoves());

        stats.getChildren().addAll(games, overall, row, size, averagewin);
        stats.setAlignment(Pos.CENTER);

        return stats;
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
