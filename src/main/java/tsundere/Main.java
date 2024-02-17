package tsundere;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tsundere.gui.MainWindow;

/**
 * A GUI for Tsundere using FXML.
 */
public class Main extends Application {

    private Tsundere tsun = new Tsundere();

    /**
     * Sets up resources required for GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Tsundere Task Assistant");
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/images/chitoge2.png"))));

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTsundere(tsun);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
