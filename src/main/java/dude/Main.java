package dude;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Main class is used to boot up the GUI for the application.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Dude");
        stage.setResizable(false);

        Scene scene = loadMainScene();
        stage.setScene(scene);

        stage.show();
    }

    /**
     * Loads the main scene of the application from the FXML file.
     *
     * @return The scene that is loaded.
     */
    private Scene loadMainScene() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainView.fxml"));
        AnchorPane anchorPane = null;
        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(anchorPane);
        return scene;
    }
}

