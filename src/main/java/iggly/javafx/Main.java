package iggly.javafx;

import java.io.IOException;

import iggly.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Iggly using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke("./data.dat");
    private final Image icon = new Image(this.getClass().getResourceAsStream("/images/Icon.png"));


    /**
     * Override of the start method in the Application class. This method is called
     * when the JavaFX application is launched. It sets up the main stage, loads the
     * main FXML file, initializes the scene, and displays the primary stage.
     *
     * @param stage The primary stage of the JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Iggly");
            stage.getIcons().add(icon);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
