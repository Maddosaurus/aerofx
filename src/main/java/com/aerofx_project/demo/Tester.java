package com.aerofx_project.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Matthias on 28.05.2014.
 */
public class Tester extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Tester.fxml"));
        primaryStage.setTitle("Some random Tester");
        Scene myScene = new Scene(root, 300, 200);
        myScene.getStylesheets().add(getClass().getResource("win7.css").toExternalForm());
        primaryStage.setScene(myScene);

        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
