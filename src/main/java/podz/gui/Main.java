package podz.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import podz.Podz;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Podz podz = new Podz();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Podz");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPodz(podz);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

