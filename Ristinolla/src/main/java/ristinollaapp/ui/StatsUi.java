package ristinollaapp.ui;

import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

        this.statsLayout.setAlignment(titel, Pos.CENTER);
        this.statsLayout.setMargin(titel, insets);
        
        this.statsLayout.setTop(titel);
        this.statsLayout.setCenter(stats());
    }

    public VBox stats() throws SQLException {
        VBox stats = new VBox(10);
        
        String[] sizeAndQuantity = this.topListLogic.mostPopularSize().split(",");
        String[] rowAndQuantity = this.topListLogic.mostPopularRow().split(",");
        Label size = new Label("Suosituin ruudukon koko on " + sizeAndQuantity[0] + ". Sillä on pelattu " + sizeAndQuantity[1] + " kertaa.");
        Label row = new Label("Suosituin voittosuoran pituus on " + rowAndQuantity[0] + ". Sillä on pelattu " + rowAndQuantity[1] + " kertaa.");
        Label overall = new Label("En tiedä suosituinta yhditelmää, sillä olen tunkero :(");
        
        stats.getChildren().addAll(overall, row, size);
        stats.setAlignment(Pos.CENTER);
        
        return stats;
    }

    public BorderPane getLayout() {
        return this.statsLayout;
    }

}
