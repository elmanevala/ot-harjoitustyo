package ristinollaapp.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ristinollaapp.domain.TopListLogic;

public class TopListUi {

    private BorderPane mainLayout;
    private BorderPane topListLayout;
    private TopListLogic topListLogic;
    private VBox topFiveLayout;

    public TopListUi(BorderPane mainLayout, TopListLogic topListLogic) throws SQLException {
        this.topFiveLayout = new VBox(10);
        this.topListLogic = topListLogic;
        this.mainLayout = mainLayout;
        this.topListLayout = new BorderPane();

        createTopListLayout();
    }

    public void createTopListLayout() throws SQLException {
        Label titel = new Label("TOP-5 voittoa, joissa käytetty vähiten siirtoja");
        titel.setFont(new Font("Arial", 20));
        Label game = new Label(this.topListLogic.getgridSize() + "x" + this.topListLogic.getgridSize() + "-ruudukko, voittosuora " + this.topListLogic.getRowSize());
        game.setFont(new Font("Arial", 15));
        
        VBox titels = new VBox(10);
        
        titels.setAlignment(Pos.CENTER);
        titels.getChildren().addAll(titel, game);

        this.topFiveLayout = setTopFive();

        this.topListLayout.setTop(titels);
        this.topListLayout.setCenter(this.topFiveLayout);
        this.topListLayout.setBottom(toStartMenu());
    }

    public void updateList() throws SQLException {
        this.topFiveLayout.getChildren().removeAll(this.topFiveLayout.getChildren());

        setTopFive();
    }

    public VBox setTopFive() throws SQLException {
        ArrayList<String> list = this.topListLogic.topFive();

        this.topFiveLayout.getChildren().add(new Label("Nimimerkki:     siirrot:"));
        for (int i = 0; i < list.size(); i++) {
            Label entry = new Label(i + 1 + ". " + list.get(i));
            entry.setAlignment(Pos.CENTER_LEFT);
            this.topFiveLayout.getChildren().add(entry);
        }

        this.topFiveLayout.setAlignment(Pos.CENTER);

        return this.topFiveLayout;
    }
    
        public Button toStartMenu() {
        Button toStart = new Button("Aloitusvalikkoon");

        toStart.setAlignment(Pos.CENTER);

        toStart.setOnAction((actionEvent -> {
            StartMenu startMenu = new StartMenu(this.mainLayout);
            this.mainLayout.setCenter(startMenu.getStartMenuLayout());
        }));

        return toStart;
    }

    public BorderPane getTopListLayout() {
        return this.topListLayout;
    }
}
