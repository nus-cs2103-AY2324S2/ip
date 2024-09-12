package yapper;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Yapper using FXML.
 */
public class Main extends Application {

    private final Yapper yapper = new Yapper();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Yapper");
            fxmlLoader.<MainWindow>getController().setYapper(yapper);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
