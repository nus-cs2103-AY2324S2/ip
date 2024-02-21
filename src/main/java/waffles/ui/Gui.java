package waffles.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import waffles.Waffles;

/**
 * GUI for Waffles chatbot using FXML.
 */
public class Gui extends Application {

    private final Waffles waffles = new Waffles();

    /**
     * Starts up the GUI.
     *
     * @param stage Stage to be used by GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Waffles");
            fxmlLoader.<MainWindow>getController().setWaffles(waffles);
            fxmlLoader.<MainWindow>getController().setWaffles(waffles);
            fxmlLoader.<MainWindow>getController().greetUser();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
