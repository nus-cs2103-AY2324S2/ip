package gui;

import java.io.IOException;

import chronos.Chronos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Chronos using FXML.
 */
public class Main extends Application {

    private Chronos chronos = new Chronos();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChronos(chronos);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
