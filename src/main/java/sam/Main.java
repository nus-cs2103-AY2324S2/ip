package sam;

import java.io.IOException;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sam.ui.MainWindow;

/**
 * The main class of the application.
 *
 * This class serves as the entry point for the JavaFX application.
 */
public class Main extends Application {
    private final Sam sam = new Sam(Paths.get("data", "Sam.txt").toAbsolutePath().toString());

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSam(sam);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
