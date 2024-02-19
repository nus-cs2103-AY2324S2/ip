import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main application class for the TaskYapper GUI.
 * It sets up the primary stage and loads the main window layout from an FXML file.
 */
public class Main extends Application {

    /**
     * Instance of TaskYapper to manage tasks.
     */
    private TaskYapper yapper = new TaskYapper("./data/taskyapper.txt");

    /**
     * Starts the application by setting up the primary stage (window) and loading the main scene from an FXML file.
     *
     * @param stage The primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTaskYapper(yapper);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
