package judy.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import judy.Judy;

/**
 * The main class starts launching the JavaFX Application and initializes the GUI components.
 */
public class Main extends Application {
    private final Judy judy = new Judy();
    private final Image judyIcon = new Image(this.getClass().getResourceAsStream("/images/Judy.png"));

    /**
     * This method is called when the JavaFX application is launched.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load main FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            // Create the scene
            Scene scene = new Scene(anchorPane);

            // Set up the stage
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Judy");
            stage.getIcons().add(judyIcon);

            // Set Duke instance in the controller
            fxmlLoader.<MainWindow>getController().setJudy(judy);

            // Show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
