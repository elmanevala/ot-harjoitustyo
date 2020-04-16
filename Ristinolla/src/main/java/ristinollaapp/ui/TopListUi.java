
package ristinollaapp.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class TopListUi {
    
    private BorderPane mainLayout;
    private BorderPane topListLayout;
    
    public TopListUi(BorderPane mainLayout){
        this.mainLayout = this.mainLayout;
        this.topListLayout = new BorderPane();
        
        createTopListLayout();
    }
    
    public void createTopListLayout() {
        Label label = new Label("TOP-5 voittoa, joissa käytetty vähiten siirtoja");

        this.topListLayout.setTop(label);
    }
    
    public BorderPane getTopListLayout() {
        return this.topListLayout;
    }
}
