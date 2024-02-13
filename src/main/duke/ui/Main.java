package duke.ui;

import java.io.IOException;

import duke.duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Skibidi using FXML.
 */
public class Main extends Application {
    private static final String FILE_PATH = "/data/";
    private static final String FILE_NAME = "duke.txt";

    /** Duke object that we will run the bot features from. */
    private Duke duke = new Duke(FILE_PATH, FILE_NAME);

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

