package model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import model.sprites.Doodle;
import model.sprites.Monster;

import java.util.ArrayList;

public class Game {

    private static final double GRAVITY = 800;
    private static final double INITIAL_VELOCITY = 0;
    private static final double DURATION = 0.0055;
    private static final double REBOUND_VELOCITY = -900;
    private static final int MIN_X = 50;
    private static final int MAX_X = 650;
    private static final int MIDPOINT = 500;


    private double currentVelocity = INITIAL_VELOCITY;


    private Doodle doodle;
    private Pane pane;
    private Monster monster;
    private Timeline timeline;
    private ArrayList<Platform> platforms;
    private int minY;
    private int maxY;
    private int score;
    private Label scoreLabel;

    public Game(Pane pane){
        this.pane = pane;
        platforms = new ArrayList<>();
        doodle = new Doodle(pane);
        monster = new Monster(pane, 300, -3000);
        this.pane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
        this.pane.setFocusTraversable(true);
        score = 0;

        // Generates platforms to start the game
        initiatePlatforms();

        setUpTimeline();
        setUpScore();
    }

    private void setUpTimeline(){
        KeyFrame kf = new KeyFrame(Duration.millis(5), new GravityHandler());
        timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setUpScore() {
        scoreLabel = new Label("Score: " + score);
        scoreLabel.setLayoutX(100);
        pane.getChildren().add(scoreLabel);
    }

    private class GravityHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event){
            updateDoodle();
            updateMonster();

            // Moves platforms down and holds doodle still if doodle flies past the midpoint of the screen
            // Deletes platforms which fall outside the screen
            // For every platform removed, generates a new platform
            if (doodle.getYLocation() < MIDPOINT) {
                double aboveMidpoint = doodle.getYLocation() - MIDPOINT;
                doodle.setYLocation(MIDPOINT);
                ArrayList<Platform> newList = new ArrayList<>(platforms);
                monster.setMonsterY(monster.getYLocation() - aboveMidpoint);
                checkGameOver();
                for (Platform p : newList) {
                    p.setPlatformY(p.getPlatformY() - aboveMidpoint);
                    if (p.getPlatformY() > 1100) {
                        platforms.remove(p);
                        score += 10;
                        scoreLabel.setText("Score: " + score);
                        maxY = platforms.get(platforms.size() - 1 ).getPlatformY();
                        minY = maxY - 100;
                        generatePlatform(minY, maxY);
                    }
                }
            }


            // If doodle touches the leftmost of the screen, set doodle position to the rightmost of the screen
            if (doodle.getXLocation() <= 0) {
                doodle.setXLocation(699);
            }

            // If doodle touches the rightmost of the screen, set doodle position to the leftmost of the screen
            if (doodle.getXLocation() >= 700) {
                doodle.setXLocation(1);
            }

            // If doodle falls 1000 pixels below the lowest platform, the game ends
            checkGameOver();
        }
    }

    private class KeyHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            KeyCode key = event.getCode();

            switch (key) {
                case RIGHT:
                    doodle.setXLocation(doodle.getXLocation() + 10);
                    break;
                case LEFT:
                    doodle.setXLocation(doodle.getXLocation() - 10);
                    break;
                default:
                    break;
            }
            event.consume();
        }
    }

    private void generatePlatform(Integer minY, Integer maxY) {
        int randomYVal = minY + (int) ((maxY - minY + 1) * Math.random());
        int randomXVal = MIN_X + (int) ((MAX_X - MIN_X + 1) * Math.random());
        Platform p = new Platform(pane, randomXVal, randomYVal);
        platforms.add(p);
    }

    private boolean anyPlatformIntersects() {
        for (Platform p : platforms) {
            if (p.platformIntersects(doodle.getXLocation(), doodle.getYLocation() + 60)) {
                return true;
            }
        }
        return false;
    }

    private void updateDoodle() {
        double updatedVelocity;
        double updatedPosition;
        if (anyPlatformIntersects() && currentVelocity > 0) {
            currentVelocity = REBOUND_VELOCITY;
            updatedVelocity = currentVelocity + GRAVITY * DURATION;
            updatedPosition = doodle.getYLocation() + updatedVelocity * DURATION;
            doodle.setYLocation(updatedPosition);
        }

        updatedVelocity = currentVelocity + GRAVITY * DURATION;
        currentVelocity = updatedVelocity;
        updatedPosition = doodle.getYLocation() + updatedVelocity * DURATION;
        doodle.setYLocation(updatedPosition);
    }

    private void updateMonster() {
        if (monster.getYLocation() > 1100) {
            int randomYVal = minY + (int) ((maxY - minY + 1) * Math.random());
            int randomXVal = MIN_X + (int) ((MAX_X - MIN_X + 1) * Math.random());
            monster.setYLocation(randomYVal);
            monster.setXLocation(randomXVal);
        }
    }

    private void initiatePlatforms() {
        minY = 900;
        maxY = 1000;
        platforms.add(new Platform(pane, 370, 430));
        while (minY > -3000) {
            generatePlatform(minY, maxY);
            maxY = platforms.get(platforms.size() - 1 ).getPlatformY();
            minY = maxY - 150;
        }
    }

    private void checkGameOver() {
        if (doodle.getYLocation() - 1000 > platforms.get(0).getPlatformY() || intersectMonster()) {
            timeline.stop();
            Button button = new Button("Try Again!");
            button.setLayoutX(325);
            button.setLayoutY(500);
            pane.getChildren().add(button);
            pane.setOnKeyPressed(null);
            button.setOnAction(event -> Main.startGame());
        }
    }

    private boolean intersectMonster() {
        return monster.monsterIntersects(doodle.getXLocation(), doodle.getYLocation());
    }


}
