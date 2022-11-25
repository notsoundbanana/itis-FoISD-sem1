package com.chemaev.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH;

    private Pane root;
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Snake game but not actually...");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
