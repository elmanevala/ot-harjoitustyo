package ristinollaapp.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import ristinollaapp.domain.GameLogic;

public class WinnerLayoutUi {

    private BorderPane winnerLayout;
    private String winner;
    private GameLogic gameLogic;

    public WinnerLayoutUi(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.winnerLayout = new BorderPane();
        this.winner = this.gameLogic.getWinner();

        createLayout();
    }

    public void createLayout() {
        Label winner = new Label("Onneksi olkoon, " + this.winner + " voitit!");

        winnerLayout.setCenter(winner);
    }

    public BorderPane getLayout() {
        return this.winnerLayout;
    }

}
