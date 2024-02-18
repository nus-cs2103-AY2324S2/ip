package scribbles.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import scribbles.Scribbles;

/**
 * A GUI for Scribbles using FXML.
 */
public class Main extends Application {

    private Scribbles scribbles = new Scribbles("./scribblesData/taskData.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.getController();
            controller.setScribbles(scribbles);

            stage.setTitle("Scribbles");

            stage.show();

            // Greet the user
            String greetingMessage = scribbles.greet();
            // Display the greeting message in the UI
            controller.displayGreetingMessage(greetingMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
