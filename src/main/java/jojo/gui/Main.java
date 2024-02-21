package jojo.gui;

import exceptions.JojoException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jojo.Jojo;

import java.io.IOException;

/**
 * A GUI for Jojo using FXML.
 */
public class Main extends Application {
    private final Jojo jojo = new Jojo("jojo.txt");

    @Override
    public void start(Stage stage) throws JojoException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJojo(jojo);
            fxmlLoader.<MainWindow>getController().showStartingMsg();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
