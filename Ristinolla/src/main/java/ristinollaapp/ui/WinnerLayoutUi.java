package ristinollaapp.ui;

import java.sql.SQLException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ristinollaapp.domain.GameLogic;
import ristinollaapp.domain.TopListLogic;

public class WinnerLayoutUi {

    private BorderPane winnerLayout;
    private String winner;
    private GameLogic gameLogic;
    private TopListLogic topListLogic;
    private BorderPane mainLayout;

    public WinnerLayoutUi(GameLogic gameLogic, BorderPane mainLayout) throws SQLException {
        this.mainLayout = mainLayout;
        this.gameLogic = gameLogic;
        this.winnerLayout = new BorderPane();
        this.winner = this.gameLogic.getWinner();
        this.topListLogic = new TopListLogic(gameLogic.getGridSize(), gameLogic.getRowSize(), gameLogic.getWinnerMoves());

        createLayout();
    }

    public void createLayout() throws SQLException{
        Label winner = new Label("Onneksi olkoon, " + this.winner + " voitit!");

        VBox buttons = new VBox(addStartMenuButton());
        buttons.setAlignment(Pos.CENTER);

        if (topListLogic.isInTopFive(gameLogic.getGridSize(), gameLogic.getRowSize(), gameLogic.getWinnerMoves())) {
            buttons.getChildren().addAll(new Text("Mahtavaa, pääsit TOP-listalle!"),addTopListButton());
        }

        winnerLayout.setCenter(winner);
        winnerLayout.setBottom(buttons);
    }

    public Button addStartMenuButton() {
        Button toStart = new Button("Aloitusvalikkoon");

        toStart.setAlignment(Pos.CENTER);

        toStart.setOnAction((actionEvent -> {
            StartMenu startMenu = new StartMenu(mainLayout);
            mainLayout.setCenter(startMenu.getStartMenuLayout());
        }));

        return toStart;
    }

    public Button addTopListButton() {
        Button toLists = new Button("Lisää nimimerkkisi!");

        toLists.setAlignment(Pos.CENTER);

        toLists.setOnAction((actionEvent -> {
            TopListUi topList = new TopListUi(mainLayout);
            mainLayout.setCenter(topList.getTopListLayout());
        }));

        return toLists;
    }

    public BorderPane getLayout() {
        return this.winnerLayout;
    }

}
