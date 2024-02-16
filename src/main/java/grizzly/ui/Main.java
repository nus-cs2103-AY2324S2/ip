package grizzly.ui;

import java.io.IOException;

import grizzly.Grizzly;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class that implements the starting up of the main User Interface
 */
public class Main extends Application {

    private Grizzly grizzly = new Grizzly();

    /**
     * Starts the bot by generating Javafx items for the user interface and linking a Grizzly object.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            System.err.println(fxmlLoader.getLocation().toString());
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            String css = this.getClass().getResource("/application.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.setTitle("Grizzly");
            fxmlLoader.<MainWindow>getController().setGrizzly(grizzly);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
