package aegis;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The code for this class was taken from the tutorial for implementing JavaFX @ https://se-education.org/guides/tutorials/javaFx.html
 *
 * Main class contains the start method for initializing the GUI for Aegis using FXML.
 */
public class Main extends Application {
    private Aegis aegis = new Aegis();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAegis(aegis);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
