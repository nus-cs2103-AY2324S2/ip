package dibo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author ziyang27-reused
//Reused from <a href="https://se-education.org/guides/tutorials/javaFxPart4.html">FXML tutorial</a>.

/**
 * A GUI for Dibo using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Dibo");
            fxmlLoader.<MainWindow>getController().setDibo();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//@@author
