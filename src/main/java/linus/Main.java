package linus;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Linus using FXML.
 */
public class Main extends Application {
    // Adapted from @Howlong11
    private Linus linus = new Linus();

    /**
     * Starts the setting of scene for GUI to load.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Linus");
            stage.setScene(scene);
            MainWindow controller = fxmlLoader.getController();
            fxmlLoader.<MainWindow>getController().setLinus(linus);
            controller.greet();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}