package ui;

import java.io.IOException;

import buddy.Buddy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Buddy buddy = new Buddy("tasks.txt");

    /**
     * Initializes the gui layout for Buddy.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Buddy");
            fxmlLoader.<MainWindow>getController().setBuddy(buddy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
