package model.sprites;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Doodle {
    private static final int FACE_RADIUS = 25;
    private static final int BODY_WIDTH = 50;
    private static final int BODY_HEIGHT = 50;
    private static final int ARM_WIDTH = 15;
    private static final int ARM_HEIGHT = 5;
    private static final int EYE_RADIUS = 2;
    private static final int FOOT_WIDTH = 7;
    private static final int FOOT_HEIGHT = 15;
    private static final int EYE_OFFSET = 5;
    private static final int FOOT_OFFSET = 15;
    private static final int ARM_OFFSET = 25;

    private static final int START_X_LOCATION = 375;
    private static final int START_Y_LOCATION = 0;

    private Circle doodleFace;
    private Rectangle doodleBody;
    private Rectangle doodleLeftArm;
    private Rectangle doodleRightArm;
    private Rectangle doodleLeftFoot;
    private Rectangle doodleRightFoot;
    private Circle doodleLeftEye;
    private Circle doodleRightEye;

    public Doodle(Pane pane){
        doodleFace = new Circle(FACE_RADIUS, Color.YELLOW);
        doodleBody = new Rectangle(BODY_WIDTH, BODY_HEIGHT, Color.BLUE);
        doodleLeftArm = new Rectangle(ARM_WIDTH, ARM_HEIGHT, Color.BLACK);
        doodleRightArm = new Rectangle(ARM_WIDTH, ARM_HEIGHT, Color.BLACK);
        doodleLeftFoot = new Rectangle(FOOT_WIDTH, FOOT_HEIGHT, Color.BLACK);
        doodleRightFoot = new Rectangle(FOOT_WIDTH, FOOT_HEIGHT, Color.BLACK);
        doodleLeftEye = new Circle(EYE_RADIUS, Color.BLACK);
        doodleRightEye = new Circle(EYE_RADIUS, Color.BLACK);
        pane.getChildren().addAll(doodleFace, doodleBody, doodleLeftArm, doodleRightArm
                , doodleLeftFoot, doodleRightFoot, doodleLeftEye, doodleRightEye);
        this.setXLocation(START_X_LOCATION);
        this.setYLocation(START_Y_LOCATION);
    }

    public void setXLocation(double x){
        doodleFace.setCenterX(x);
        doodleBody.setX(x - FACE_RADIUS);
        doodleLeftArm.setX(x - ARM_WIDTH - ARM_OFFSET);
        doodleRightArm.setX(x + ARM_OFFSET);
        doodleLeftFoot.setX(x - FOOT_OFFSET - FOOT_WIDTH);
        doodleRightFoot.setX(x + FOOT_OFFSET);
        doodleLeftEye.setCenterX(x - EYE_OFFSET);
        doodleRightEye.setCenterX(x + EYE_OFFSET);
    }

    public void setYLocation(double y) {
        doodleFace.setCenterY(y);
        doodleBody.setY(y + FACE_RADIUS/2);
        doodleLeftArm.setY(y + ARM_OFFSET);
        doodleRightArm.setY(y + ARM_OFFSET);
        doodleLeftFoot.setY(y + FOOT_OFFSET * 4);
        doodleRightFoot.setY(y +FOOT_OFFSET * 4);
        doodleLeftEye.setCenterY(y);
        doodleRightEye.setCenterY(y);
    }

    public double getXLocation(){return doodleFace.getCenterX();}

    public double getYLocation(){return doodleFace.getCenterY();}


}
