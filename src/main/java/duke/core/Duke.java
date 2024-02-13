package duke.core;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML and JavaFX to display the chatbot.
 * Solution below adapted from https://se-education.org/guides/tutorials/javaFx.html
 */
public class Duke extends Application {
    private Chatbot tribara = new Chatbot("Tribara");
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().initialise(tribara);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

