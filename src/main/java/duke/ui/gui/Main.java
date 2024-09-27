package duke.ui.gui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            // to apply the CSS file to the scene
            scene.getStylesheets().add(Main.class.getResource("/duke.css").toExternalForm());

            // Prevent the window from being resized
            stage.setResizable(false);

            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(Duke.getInstance());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
