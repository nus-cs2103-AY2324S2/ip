package friday.main;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main class for launching the GUI application for Friday using FXML.
 */
public class Main extends Application {
    private Friday friday = new Friday();

    /**
     * The entry point for launching the GUI application.
     * Initializes the necessary components and sets up the scene using FXML.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            assert friday != null : "Friday instance must not be null";
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert fxmlLoader != null : "FXML loader must be initialized";
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "AnchorPane must be initialized";
            Scene scene = new Scene(ap);
            assert scene != null : "Scene must be initialized";
            stage.setScene(scene);
            stage.setTitle("Friday");
            fxmlLoader.<MainWindow>getController().setFriday(friday);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
