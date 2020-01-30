package model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    static Stage window;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Doodle Jump");

        startGame();
    }

    public static void startGame() {
        PaneOrganizer pane = new PaneOrganizer();
        window.setScene(new Scene(pane.getRoot(), 750, 1000));
        window.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
