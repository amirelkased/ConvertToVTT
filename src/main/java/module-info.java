module com.elkased.converttovtt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.elkased.converttovtt to javafx.fxml;
    exports com.elkased.converttovtt;
    exports com.elkased.converttovtt.controller;
    opens com.elkased.converttovtt.controller to javafx.fxml;
    exports com.elkased.converttovtt.model;
    opens com.elkased.converttovtt.model to javafx.fxml;
}