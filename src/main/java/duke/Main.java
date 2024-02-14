package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Hari using FXML.
 */

public class Main extends Application {

    private final Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Window.fxml"));
            AnchorPane anchoringPane = fxmlLoader.load();

            // Set controller data
            MainDisplay controller = fxmlLoader.<MainDisplay>getController();
            controller.setDuke(duke);

            Scene scene = new Scene(anchoringPane);
            stage.setScene(scene);
            stage.setTitle("Hari");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
