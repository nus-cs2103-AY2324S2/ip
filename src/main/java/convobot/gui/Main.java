package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The entry point for the ConvoBot GUI Application using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("ConvoBot");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/DaConvo.png")));

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
