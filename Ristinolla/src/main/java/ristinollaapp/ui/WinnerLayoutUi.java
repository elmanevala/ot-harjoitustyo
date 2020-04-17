package ristinollaapp.ui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    public void createLayout() throws SQLException {
        Label winner = new Label("Onneksi olkoon, " + this.winner + " voitit!");
        Insets insets = new Insets(100);
        winnerLayout.setAlignment(winner, Pos.CENTER);

        VBox buttons = new VBox(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(insets);

        if (topListLogic.isInTopFive()) {
            TextField winnerName = nameField();
            buttons.getChildren().addAll(new Text("Mahtavaa, pääsit TOP-listalle!"), winnerName, addTopListButton(winnerName));
        } else {
            buttons.getChildren().addAll(addStartMenuButton());
        }

        winnerLayout.setMargin(winner, new Insets(50));
        winnerLayout.setTop(winner);
        winnerLayout.setCenter(buttons);
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

    public Button addTopListButton(TextField name) {
        Button toLists = new Button("Lisää nimimerkkisi!");

        toLists.setAlignment(Pos.CENTER);

        toLists.setOnAction((actionEvent -> {
            TopListUi topList;
            try {
                topList = new TopListUi(mainLayout, this.topListLogic);
                this.topListLogic.addName(name.getText());
                topList.updateList();
                mainLayout.setCenter(topList.getTopListLayout());
            } catch (SQLException ex) {
                Logger.getLogger(WinnerLayoutUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));

        return toLists;
    }

    public TextField nameField() {
        TextField winnerName = new TextField("nimimerkki");
        winnerName.setAlignment(Pos.CENTER);
        winnerName.setMaxWidth(120);

        return winnerName;
    }

    public BorderPane getLayout() {
        return this.winnerLayout;
    }

}
