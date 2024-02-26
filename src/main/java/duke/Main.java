package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for the Duke application.
 * This class serves as the entry point for the application and sets up the GUI.
 */
public class Main extends Application {

    private Duke duke;

    /**
     * Constructor for the Main class.
     * It initializes the Duke instance.
     */
    public Main() {
        try {
            duke = new Duke();
        } catch (BotException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned, and after the
     * system is ready for the application to begin running.
     * 
     * @param stage the primary stage for this application, onto which the
     *              application scene can be set
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
