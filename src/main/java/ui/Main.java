package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jux.Jux;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Jux jux = new Jux("data/Jux.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Jux");
            fxmlLoader.<MainWindow>getController().setJux(jux);
            fxmlLoader.<MainWindow>getController().welcomeMessage(); // initialise the task list here instead of main window
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
