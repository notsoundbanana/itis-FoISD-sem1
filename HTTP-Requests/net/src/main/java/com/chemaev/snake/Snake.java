package com.chemaev.snake;

import javafx.scene.shape.Rectangle;

public class Snake extends Rectangle {

    private int snakeCurrentWidth;
    private int snakeCurrentHeight;
    private static final int STEP = 10;
    private static final int FOOD = 10;
    private Direction currentDirection;

    public Snake(double v, double v1, int snakeCurrentWidth, int snakeCurrentHeight) {
        super(v, v1, snakeCurrentWidth, snakeCurrentHeight);
        this.snakeCurrentWidth = snakeCurrentWidth;
        this.snakeCurrentHeight = snakeCurrentHeight;
        this.currentDirection = Direction.UP;
    }

    public void step() {
        if (currentDirection == Direction.UP) {
            setY(getY() - STEP);
        } else if (currentDirection == Direction.DOWN) {
            setY(getY() + STEP);
        } else if (currentDirection == Direction.LEFT) {
            setX(getX() - STEP);
        } else if (currentDirection == Direction.RIGHT) {
            setX(getX() + STEP);
        }
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void eat() {
        snakeCurrentWidth += FOOD;
        snakeCurrentHeight += FOOD;
    }

    public int getSnakeCurrentWidth() {
        return snakeCurrentWidth;
    }

    public int getSnakeCurrentHeight() {
        return snakeCurrentHeight;
    }

    public void setSnakeCurrentWidth(int snakeCurrentWidth) {
        this.snakeCurrentWidth = snakeCurrentWidth;
    }

    public void setSnakeCurrentHeight(int snakeCurrentHeight) {
        this.snakeCurrentHeight = snakeCurrentHeight;
    }
}
