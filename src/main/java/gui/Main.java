package gui;

import java.io.IOException;

import destiny.Destiny;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Destiny using FXML.
 */
public class Main extends Application {

    private Destiny destiny = new Destiny();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            String css = Main.class.getResource("/application.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDestiny(destiny);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
