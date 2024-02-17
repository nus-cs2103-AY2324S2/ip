package headcube;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main entry point for the HeadCube JavaFX application.
 * This class is responsible for loading the FXML layout, initializing the scene,
 * and displaying the primary stage.
 */
public class Main extends Application {
    /**
     * Starts the JavaFX application by setting up the primary stage.
     * This method attempts to load the FXML layout for the MainWindow,
     * sets the scene on the primary stage, and displays it.
     * It also initializes the HeadCube instance and sets it in the MainWindow controller.
     *
     * @param stage The primary stage for this application, onto which the application scene can be set.
     *              Applications may create other stages if needed, but they will not be primary stages.
     * @throws IOException If there is an issue loading the FXML file, an IOException is thrown.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHeadCube(new HeadCube());
            stage.setTitle("HeadCube");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
