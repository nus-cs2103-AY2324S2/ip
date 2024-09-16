// @@author hjuntan-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with modifications

package toothless;

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

    private Toothless toothless = new Toothless();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setToothless(toothless);
            fxmlLoader.<MainWindow>getController().startUp();
            stage.setTitle("Toothless");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// @@ author