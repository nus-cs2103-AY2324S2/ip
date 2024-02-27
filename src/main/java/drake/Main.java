package drake;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main is the entry point for the Drake application.
 *
 * @see javafx.application.Application
 * @see javafx.fxml.FXMLLoader
 * @see javafx.scene.Scene
 * @see javafx.stage.Stage
 */
public class Main extends Application {

    /**
     * The main application logic handler. This instance is responsible for managing the application's
     * state, data, and responding to user actions.
     */
    private final Drake drake = new Drake();

    /**
     * Starts and sets up the primary stage of the application, including loading the FXML layout,
     * setting the scene, and initializing the controller.
     *
     * @param stage The primary stage for this application, onto which the application scene is set.
     *              The primary stage is configured with a title and the FXML-defined layout, and then
     *              shown to the user.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDrake(drake);
            stage.setTitle("Drake");
            stage.show();
            // Set welcome message after the UI has been displayed.
            fxmlLoader.<MainWindow>getController().setWelcomeMessage(Ui.showWelcome());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
