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
    private VBox topFiveLayout;

    public TopListUi(BorderPane mainLayout, TopListLogic topListLogic) throws SQLException {
        this.topFiveLayout = new VBox(10);
        this.topListLogic = topListLogic;
        this.mainLayout = this.mainLayout;
        this.topListLayout = new BorderPane();

        createTopListLayout();
    }

    public void createTopListLayout() throws SQLException {
        Label label = new Label("TOP-5 voittoa, joissa käytetty vähiten siirtoja");

        this.topFiveLayout = setTopFive();

        this.topListLayout.setTop(label);
        this.topListLayout.setCenter(this.topFiveLayout);
    }
    
    public void updateList() throws SQLException{
        this.topFiveLayout.getChildren().removeAll(this.topFiveLayout.getChildren());
        
        setTopFive();
    }

    public VBox setTopFive() throws SQLException {
        ArrayList<String> list = this.topListLogic.topFive();
        
        this.topFiveLayout.getChildren().add(new Label("Nimimerkki:     siirrot:"));
        for (int i = 0; i < list.size(); i++) {
            Label entry = new Label(i + 1 + ". " + list.get(i));
            this.topFiveLayout.getChildren().add(entry);
        }
        
        this.topFiveLayout.setAlignment(Pos.CENTER);

        
        return this.topFiveLayout;
    }

    public BorderPane getTopListLayout() {
        return this.topListLayout;
    }
}
