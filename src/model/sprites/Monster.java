package model.sprites;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Monster {
    private static final int FACE_RADIUS = 75;
    private static final int EYE_RADIUS = 25;
    private static final int IRIS_RADIUS = 10;
    private static final int FOOT_WIDTH = 20;
    private static final int FOOT_HEIGHT = 8;
    private static final int FOOT_OFFSET = 15;
    private static final int ARM_OFFSET = 25;
    private static final int EYE_OFFSET = 25;

    private Circle monsterFace;
    private Rectangle monsterMouth;
    private Circle monsterLeftEye;
    private Circle monsterRightEye;
    private Circle monsterRightIris;
    private Circle monsterLeftIris;

    public Monster(Pane pane, int xLoc, int yLoc){
        monsterFace = new Circle(FACE_RADIUS, Color.RED);
        monsterMouth = new Rectangle(FOOT_WIDTH, FOOT_HEIGHT, Color.BLACK);
        monsterLeftEye = new Circle(EYE_RADIUS, Color.BLACK);
        monsterRightEye = new Circle(EYE_RADIUS, Color.BLACK);
        monsterRightIris = new Circle(IRIS_RADIUS, Color.GREENYELLOW);
        monsterLeftIris = new Circle(IRIS_RADIUS, Color.GREENYELLOW);
        pane.getChildren().addAll(monsterFace, monsterMouth, monsterLeftEye
                , monsterRightEye, monsterLeftIris, monsterRightIris);
        this.setXLocation(xLoc);
        this.setYLocation(yLoc);
    }

    public void setXLocation(double x){
        monsterFace.setCenterX(x);
        monsterMouth.setX(x - FOOT_OFFSET);
        monsterLeftEye.setCenterX(x - EYE_OFFSET);
        monsterRightEye.setCenterX(x + 2*FOOT_OFFSET);
        monsterRightIris.setCenterX(x + 2*FOOT_OFFSET);
        monsterLeftIris.setCenterX(x - EYE_OFFSET);
    }

    public void setYLocation(double y) {
        monsterFace.setCenterY(y);
        monsterMouth.setY(y + ARM_OFFSET);
        monsterLeftEye.setCenterY(y - FOOT_OFFSET);
        monsterRightEye.setCenterY(y);
        monsterRightIris.setCenterY(y);
        monsterLeftIris.setCenterY(y - FOOT_OFFSET);
    }

    public double getXLocation(){return monsterFace.getCenterX();}

    public double getYLocation(){return monsterFace.getCenterY();}

    public void setMonsterY (double y) {
        setYLocation(y);
    }
}
