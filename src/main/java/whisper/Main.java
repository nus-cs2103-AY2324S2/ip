package whisper;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * The main class for the Whisper application, responsible for initializing and launching the JavaFX user interface.
 */
public class Main extends Application {

    /**
     * The instance of the Whisper class that manages the application's logic.
     */
    private Whisper whisper = new Whisper();

    /**
     * The main entry point for the application.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file for the main window
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            // Load the root AnchorPane from the FXML file
            AnchorPane ap = fxmlLoader.load();

            // Create a new scene with the loaded AnchorPane
            Scene scene = new Scene(ap);

            // Set the scene to the primary stage
            stage.setScene(scene);
            stage.setTitle("Whisper");

            // Set the Whisper instance in the controller
            fxmlLoader.<MainWindow>getController().setWhisper(whisper);

            // Show the primary stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

