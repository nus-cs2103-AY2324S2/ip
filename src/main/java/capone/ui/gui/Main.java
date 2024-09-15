package capone.ui.gui;

import java.io.IOException;

import capone.Capone;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Capone using FXML.
 */
public class Main extends Application {
    /** The Capone instance associated with this application. */
    private Capone capone = new Capone();

    /**
     * The start method of the JavaFX application.
     * It loads the main window FXML file, sets up the scene, and displays the main window.
     *
     * @param stage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(Capone.getName());
            fxmlLoader.<MainWindow>getController().setCapone(this.capone);
            fxmlLoader.<MainWindow>getController().runCapone(this.capone);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
