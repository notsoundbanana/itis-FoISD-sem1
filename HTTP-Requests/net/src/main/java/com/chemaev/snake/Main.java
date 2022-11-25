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

    public static final int SNAKE_START_WIDTH = 10;
    public static final int SNAKE_START_HEIGHT = 10;
    private static final int FOOD_WIDTH = 10;
    private static final int FOOD_HEIGHT = 10;


    private Pane root;
    private Random random;
    private Rectangle food;
    private Snake snake;


    private void newFood() {
        food = new Rectangle(random.nextInt(WIDTH), random.nextInt(HEIGHT), FOOD_WIDTH, FOOD_HEIGHT);
        food.setFill(Color.RED);
        root.getChildren().add(food);
    }

    private void deleteFood(Rectangle food) {
        root.getChildren().remove(food);
    }

    public void newSnake(int width, int height, int snakeWidth, int snakeHeight) {
        snake = new Snake(width, height, snakeWidth, snakeHeight);
        snake.setFill(Color.BLACK);
        root.getChildren().add(snake);

    }

    private boolean hit() {
        return food.intersects(snake.getBoundsInLocal());
    }

    private void move() {
        Platform.runLater(() -> {
            snake.step();
            adjustLocation();
            if (hit()) {
                snake.eat();
                increaseSizeOfSnake();
                deleteFood(food);
                newFood();
            }
        });
    }

    private void adjustLocation() {
        if (snake.getX() < 0) {
            snake.setX(WIDTH);
        } else if (snake.getX() > WIDTH) {
            snake.setX(0);
        }
        if (snake.getY() < 0) {
            snake.setY(HEIGHT);
        } else if (snake.getY() > HEIGHT) {
            snake.setY(0);
        }
    }

    private void increaseSizeOfSnake() {
        if (snake.getSnakeCurrentWidth() < WIDTH || snake.getSnakeCurrentHeight() < HEIGHT) {
            snake.widthProperty().setValue(snake.getSnakeCurrentWidth());
            snake.heightProperty().setValue(snake.getSnakeCurrentHeight());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);
        random = new Random();

        newFood();
        newSnake(WIDTH / 2, HEIGHT / 2, SNAKE_START_WIDTH, SNAKE_START_HEIGHT);

        Runnable r = () -> {
            try {
                for (; ; ) {
                    move();
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(key -> {
            switch (key.getCode()) {
                case UP -> snake.setCurrentDirection(Direction.UP);
                case DOWN -> snake.setCurrentDirection(Direction.DOWN);
                case LEFT -> snake.setCurrentDirection(Direction.LEFT);
                case RIGHT -> snake.setCurrentDirection(Direction.RIGHT);
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
