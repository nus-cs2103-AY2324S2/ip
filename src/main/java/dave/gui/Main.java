package dave.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import dave.Dave;

// @@author iynixil-reused
//     from https://se-education.org/guides/tutorials/javaFx.html
//     (all parts of the tutorial) with minor modifications
/**
 * A GUI for Dave using FXML.
 */
public class Main extends Application {

    private Dave dave = new Dave();

    private static final String APP_NAME = "Dave";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(APP_NAME);
            fxmlLoader.<MainWindow>getController().setDave(dave);
            fxmlLoader.<MainWindow>getController().showGreeting();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
