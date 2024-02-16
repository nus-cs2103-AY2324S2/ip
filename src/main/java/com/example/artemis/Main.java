package com.example.artemis;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main class for the Artemis GUI application.
 * Launches the JavaFX application and initializes the main window.
 */
public class Main extends Application {

    private Artemis artemis = new Artemis();

    /**
     * Starts the JavaFX application, initializes the main window,
     * and sets up the Artemis instance.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Artemis");
        stage.setResizable(false);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setArtemis(artemis);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

