package duke.ui.gui;

import java.io.IOException;

import duke.Duke;
import duke.exceptions.DukeException;
import duke.exceptions.storage.LoadTasksFailedException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class serves as the entry point for the Duke GUI application.
 * It initializes the Duke instance and loads the main FXML layout to display the application window.
 */
public class Main extends Application {
    private static Duke duke;

    /**
     * Initializes the Duke instance for the GUI application.
     *
     * @param duke The Duke instance to be used by the GUI application.
     */
    public static void init(Duke duke) {
        Main.duke = duke;
    }

    /**
     * Starts the Duke GUI application.
     * Loads the main FXML layout, sets up the scene, and displays the application window.
     *
     * @param stage The primary stage for the JavaFX application.
     * @throws DukeException If an error occurs while loading the FXML layout.
     */
    @Override
    public void start(Stage stage) throws DukeException {
        try {
            // Load the main FXML layout
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // Set up the scene and display the application window
            stage.setScene(scene);
            stage.show();

            // Set the Duke instance for the controller
            fxmlLoader.<MainWindow>getController().setDuke(Main.duke);
        } catch (IOException ioException) {
            // Throw a custom exception if loading the FXML layout fails
            throw new LoadTasksFailedException(ioException);
        }
    }
}
