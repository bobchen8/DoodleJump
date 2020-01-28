package model;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;

import java.awt.*;

public class PaneOrganizer {
    private BorderPane borderPane;
    private Pane pane;

    public PaneOrganizer() {
        this.borderPane = new BorderPane();
        this.pane = new Pane();
        pane.setPrefSize(700, 950);
        borderPane.setCenter(pane);
        new Game(pane);

        setUpTop();
    }

    public Pane getRoot(){
        return borderPane;
    }

    private void setUpTop(){
        HBox topPane = new HBox();
        borderPane.setTop(topPane);

        Button b1 = new Button("Quit");

        topPane.getChildren().addAll(b1);
        topPane.setAlignment(Pos.TOP_LEFT);
        b1.setOnAction(e -> System.exit(0));
        topPane.setFocusTraversable(false);

    }

}
