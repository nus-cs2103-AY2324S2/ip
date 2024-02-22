package jade.gui;

import java.io.IOException;

import jade.Jade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main class responsible for starting the Jade GUI application.
 * Some comments are improved using ChatGPT 3.5 using the prompt:
 * "add comments for the following class: {code}"
 */
public class Main extends Application {
    // Path to the FXML file defining the main window
    private static final String FXML_FILE_PATH = "/view/MainWindow.fxml";
    // Instance of the Jade class to manage the underlying logic
    private final Jade jade = new Jade();

    /**
     * The entry point of the application, responsible for initializing and displaying the main window.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file for the main window
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FXML_FILE_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            // Set the scene to the primary stage
            stage.setScene(scene);
            stage.setTitle("Jade");
            // Get the controller associated with the FXML file and set the Jade instance
            fxmlLoader.<MainWindow>getController().setJade(jade);
            // Display the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
