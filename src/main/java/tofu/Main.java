package tofu;

import java.io.IOException;
import tofu.ui.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Tofu using FXML.
 */
public class Main extends Application {

    private Tofu tofu = new Tofu();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("\uD83D\uDC3E Tofu List");

            fxmlLoader.<MainWindow>getController().setTofu(tofu);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

