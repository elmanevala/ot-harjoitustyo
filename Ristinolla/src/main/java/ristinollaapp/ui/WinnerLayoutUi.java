package ristinollaapp.ui;

import java.sql.SQLException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
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

    public void createLayout() {
        Label winner = new Label("Onneksi olkoon, " + this.winner + " voitit!");

        winnerLayout.setCenter(winner);
        winnerLayout.setBottom(addStarMenuButton());
    }

    public Button addStarMenuButton() {
        Button toStart = new Button("Aloitusvalikkoon");

        toStart.setOnAction((actionEvent -> {
            StartMenu startMenu= new StartMenu(mainLayout);
            mainLayout.setCenter(startMenu.getStartMenuLayout());
        }));

        return toStart;
    }

    public BorderPane getLayout() {
        return this.winnerLayout;
    }

}
