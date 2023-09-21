package com.elkased.converttovtt.controller;

import com.elkased.converttovtt.model.FileConverter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class MainController {
    private List<String> selectedFilePaths;

    @FXML
    private Stage primaryStage;

    @FXML
    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a File(s)");
        selectedFilePaths = fileChooser.showOpenMultipleDialog(primaryStage).stream().map(File::getAbsolutePath).toList();
    }

    @FXML
    private void performFileOperation() {
        if (selectedFilePaths == null || selectedFilePaths.isEmpty()) {
            displayFailureMessage("No files selected.");
            return;
        }

        for (String selectedFilePath : selectedFilePaths) {

            String newFileName = FileConverter.doConvert(selectedFilePath);
            if (newFileName != null) {
                displaySuccessMessage("Operation Successful for file: " + selectedFilePath);
            } else {
                displayFailureMessage("Operation Failed for file: " + selectedFilePath);
            }
        }
    }

    private void displaySuccessMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void displayFailureMessage(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
