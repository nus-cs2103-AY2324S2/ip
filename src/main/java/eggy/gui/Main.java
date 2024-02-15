package eggy.gui;

import java.io.IOException;

import eggy.Eggy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Eggy using FXML.
 */
public class Main extends Application {
    private Eggy eggy = new Eggy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            scene.getStylesheets().add("/css/styles.css");

            stage.setScene(scene);
            stage.setMinHeight(420);
            stage.setMinWidth(280);
            stage.setTitle("Eggy");

            fxmlLoader.<MainWindow>getController().setEggy(eggy);
            fxmlLoader.<MainWindow>getController().showInitMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
