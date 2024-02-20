package dino;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Dino using FXML.
 */
public class Main extends Application {

    private Dino dino = new Dino();
    @Override
    public void start(Stage stage) {
        assert dino != null : "Dino cannot be null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setTitle("Dino");
            fxmlLoader.<MainWindow>getController().setDino(dino);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

