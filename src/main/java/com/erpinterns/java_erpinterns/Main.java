package com.erpinterns.java_erpinterns;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HeadmasterForm.fxml")));
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/erpinterns/java_erpinterns/HeadmasterForm.fxml")));
        primaryStage.setTitle("Headmaster Form");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
