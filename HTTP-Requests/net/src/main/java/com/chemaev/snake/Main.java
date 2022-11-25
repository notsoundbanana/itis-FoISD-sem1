package com.chemaev.snake;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private static final int FOOD_WIDTH = 10;
    private static final int FOOD_HEIGHT = 10;

    private static final int SNAKE_WIDTH = 10;
    private static final int SNAKE_HEIGHT = 10;

    private static final int STEP = 10;

    private Pane root;
    private Random random;
    private Rectangle food;
    private Rectangle snake;

    private Direction currentDirection;


    private void newFood() {
        food = new Rectangle(random.nextInt(WIDTH), random.nextInt(HEIGHT), FOOD_WIDTH, FOOD_HEIGHT);
        food.setFill(Color.RED);
        root.getChildren().add(food);
    }

    private void newSnake() {
        snake = new Rectangle(WIDTH / 2, HEIGHT / 2, SNAKE_WIDTH, SNAKE_HEIGHT);
        snake.setFill(Color.BLACK);
        root.getChildren().add(snake);
    }

    private void step() {
        if (currentDirection == Direction.UP) {
            snake.setY(snake.getY() - STEP);
        } else if (currentDirection == Direction.DOWN) {
            snake.setY(snake.getY() + STEP);
        } else if (currentDirection == Direction.LEFT) {
            snake.setX(snake.getX() - STEP);
        } else if (currentDirection == Direction.RIGHT) {
            snake.setX(snake.getX() + STEP);
        }
    }

    private void move() {
        Platform.runLater(() -> {
            step();
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);
        random = new Random();
        currentDirection = Direction.UP;

        newFood();
        newSnake();

        Runnable r = () -> {
            try {
                for (; ; ) {
                    move();
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(key -> {
            switch (key.getCode()) {
                case UP -> currentDirection = Direction.UP;
                case DOWN -> currentDirection = Direction.DOWN;
                case LEFT -> currentDirection = Direction.LEFT;
                case RIGHT -> currentDirection = Direction.RIGHT;
            }
        });

        primaryStage.setTitle("Snake game but not actually...");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        thread.start();
    }
}
