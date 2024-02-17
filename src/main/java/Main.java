import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main class responsible for launching the JavaFX application.
 */
public class Main extends Application {

    // Instance of Duke
    private final Duke duke = new Duke();

    // Application icon
    private final Image icon = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/snorlax.png")));

    /**
     * The entry point for launching the JavaFX application.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the main FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Create the scene
            Scene scene = new Scene(ap);

            // Set the scene to the stage
            stage.setScene(scene);
            stage.setTitle("Snorlax"); // Set the stage title
            stage.getIcons().add(icon); // Set the stage icon
            stage.setResizable(false); // Make the stage non-resizable

            // Set Duke instance in the controller
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            // Show the stage
            stage.show();

        } catch (IOException e) {
            // Handle IO exception
            e.printStackTrace();
        }
    }
}
