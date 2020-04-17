package ristinollaapp.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ristinollaapp.domain.TopListLogic;

public class TopListUi {

    private BorderPane mainLayout;
    private BorderPane topListLayout;
    private TopListLogic topListLogic;

    public TopListUi(BorderPane mainLayout, TopListLogic topListLogic) throws SQLException {
        this.topListLogic = topListLogic;
        this.mainLayout = this.mainLayout;
        this.topListLayout = new BorderPane();

        createTopListLayout();
    }

    public void createTopListLayout() throws SQLException {
        Label label = new Label("TOP-5 voittoa, joissa käytetty vähiten siirtoja");

        VBox topfiveLayout = new VBox(10);

        topfiveLayout = setTopFive(topfiveLayout);

        this.topListLayout.setTop(label);
        this.topListLayout.setCenter(topfiveLayout);
    }

    public VBox setTopFive(VBox layout) throws SQLException {
        ArrayList<String> list = this.topListLogic.topFive();
        
        layout.getChildren().add(new Label("Nimimerkki:     siirrot:"));
        for (int i = 0; i < list.size(); i++) {
            Label entry = new Label(i + 1 + ". " + list.get(i));
            layout.getChildren().add(entry);
        }
        
        layout.setAlignment(Pos.CENTER);

        
        return layout;
    }

    public BorderPane getTopListLayout() {
        return this.topListLayout;
    }
}
