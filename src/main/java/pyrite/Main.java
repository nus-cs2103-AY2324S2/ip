package pyrite;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Pyrite using FXML.
 */
public class Main extends Application {
    private Pyrite pyrite = new Pyrite();

    /**
     * Starts the application.
     *
     * @param stage The JavaFX stage.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(pyrite.getName());
            fxmlLoader.<MainWindow>getController().setPyrite(pyrite);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
