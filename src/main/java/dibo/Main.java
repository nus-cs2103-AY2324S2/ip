package dibo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Dibo using FXML.
 * Referenced <a href="https://se-education.org/guides/tutorials/javaFxPart4.html">FXML tutorial</a>.
 */
public class Main extends Application {

    private Dibo dibo = new Dibo();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDibo(dibo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

