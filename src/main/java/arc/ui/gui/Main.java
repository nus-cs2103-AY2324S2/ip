package arc.ui.gui;

import arc.Arc;
import java.io.IOException;

import arc.exceptions.ArcException;
import arc.exceptions.storage.LoadTasksFailedException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class serves as the entry point for the Arc GUI application.
 * It initializes the Arc instance and loads the main FXML layout to display the application window.
 */
public class Main extends Application {
    private static Arc arc;

    /**
     * Initializes the Arc instance for the GUI application.
     *
     * @param arc The Arc instance to be used by the GUI application.
     */
    public static void init(Arc arc) {
        Main.arc = arc;
    }

    /**
     * Starts the Arc GUI application.
     * Loads the main FXML layout, sets up the scene, and displays the application window.
     *
     * @param stage The primary stage for the JavaFX application.
     * @throws ArcException If an error occurs while loading the FXML layout.
     */
    @Override
    public void start(Stage stage) throws ArcException {
        try {
            // Load the main FXML layout
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // Set up the scene and display the application window
            stage.setScene(scene);
            stage.show();

            // Set the Arc instance for the controller
            fxmlLoader.<MainWindow>getController().setArc(Main.arc);
        } catch (IOException ioException) {
            // Throw a custom exception if loading the FXML layout fails
            throw new LoadTasksFailedException(ioException);
        }
    }
}
