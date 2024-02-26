package liv;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import liv.ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Liv liv = new Liv("./data/Liv.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            Image iconImage = new Image(this.getClass().getResourceAsStream("/images/icon.png"));

            stage.setTitle("Liv");
            stage.getIcons().add(iconImage);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLiv(liv);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


