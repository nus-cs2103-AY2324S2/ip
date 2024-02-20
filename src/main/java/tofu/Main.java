package tofu;

import java.io.IOException;

import javafx.scene.image.Image;
import tofu.ui.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the main application for the Tofu GUI using FXML.
 * This class is responsible for initializing the application and loading the main window.
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
            stage.setTitle("Tofu the Cat");

            Image tofuImage = new Image(this.getClass().getResourceAsStream("/images/icon.png"));
            stage.getIcons().add(tofuImage);

            fxmlLoader.<MainWindow>getController().setTofu(tofu);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

