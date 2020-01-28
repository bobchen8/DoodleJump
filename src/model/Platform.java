package model;

import java.awt.*;
import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;

public class Platform {

    private Ellipse ellipse;
    private static final int PLATFORM_WIDTH = 45;
    private static final int PLATFORM_HEIGHT = 7;

    public Platform(Pane pane, Integer x, Integer y) {
        this.ellipse = new Ellipse(PLATFORM_WIDTH, PLATFORM_HEIGHT);
        this.ellipse.setFill(Color.GREEN);
        pane.getChildren().addAll(ellipse);
        ellipse.setCenterY(y);
        ellipse.setCenterX(x);
    }

    public boolean platformIntersects(double x, double y) {
        return ellipse.intersects(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
    }

    public int getPlatformX() {
        return (int) ellipse.getCenterX();
    }

    public int getPlatformY() {
        return (int) ellipse.getCenterY();
    }

    public void setPlatformY(double y) {
        ellipse.setCenterY(y);
    }


}
