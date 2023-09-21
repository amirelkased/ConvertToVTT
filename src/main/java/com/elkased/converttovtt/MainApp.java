package com.elkased.converttovtt;

import com.elkased.converttovtt.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/elkased/converttovtt/main.fxml"));
        Scene scene = new Scene(loader.load(), 500, 300);
        stage.setTitle("Converter To VTT");
        stage.setScene(scene);

        MainController controller = loader.getController();
        controller.setPrimaryStage(stage);

        stage.show();
    }
}

