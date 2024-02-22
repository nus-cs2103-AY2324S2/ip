package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class is used to launch the GUI (JavaFX - FXML) of the Duke application
 */

public class Main extends Application {

    private final Duke duke = new Duke();

    /**
     *
     * @param stage Primary stage for the JavaFX - FXML Duke application
     */

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchoringPane = fxmlLoader.load();

            Scene scene = new Scene(anchoringPane);
            stage.setScene(scene);
            stage.setTitle("HariUp");

            // Set controller data
            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setDuke(duke);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
