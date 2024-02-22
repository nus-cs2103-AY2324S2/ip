package duke.main;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class is the entry point of the Duke application.
 * It initializes the necessary components and starts the application.
 */
public class Main extends Application {
    private Duke duke = new Duke();

    /**
     * Starts the Duke application.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the MainWindow.fxml file using FXMLLoader
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Create a new scene with the AnchorPane
            Scene scene = new Scene(ap);

            // Set the scene to the primary stage
            stage.setScene(scene);

            // Get the controller of the MainWindow.fxml file and set the Duke object
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            // Call the customisedInitialise method of the controller
            fxmlLoader.<MainWindow>getController().customisedInitialise();

            // Show the primary stage
            stage.show();
        } catch (IOException e) {
            // Print the stack trace if an IOException occurs during loading
            e.printStackTrace();
        }
    }
}

