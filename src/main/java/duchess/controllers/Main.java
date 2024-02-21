package duchess.controllers;
import java.io.IOException;

import duchess.Duchess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duchess using FXML.
 */
public class Main extends Application {
    private Duchess duchess = new Duchess();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("\uD83D\uDC51 Duchess Task Manager \uD83D\uDC51");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuchess(duchess);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
