package com.aerofx_project.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Matthias on 27.05.2014.
 */
public class SystemSettingsDemo extends Application {
    /**
     *
     * @param primaryStage  The primary Stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SystemSettingsDemo.fxml"));
        primaryStage.setTitle("Systemeigenschaften");
        Scene myScene = new Scene(root, 447, 560);
        myScene.getStylesheets().add(getClass().getResource("win7.css").toExternalForm());
        primaryStage.setScene(myScene);

        primaryStage.show();
    }
}
