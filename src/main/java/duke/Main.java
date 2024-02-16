package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();


    /**
     * Starts the Duke application.
     *
     * @param stage The stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file for MainWindow
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Get the controller and set Duke
            MainWindow mainWindow = fxmlLoader.getController();
            Duke duke = new Duke();
            mainWindow.setDuke(duke);

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("MazeDeneroBot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




