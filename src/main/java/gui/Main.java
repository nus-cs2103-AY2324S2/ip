package gui;

import java.io.IOException;

import chrisPBacon.ChrisPBacon;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private ChrisPBacon chrisPBacon = new ChrisPBacon();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Chris P Bacon :)");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChrisPBacon(chrisPBacon);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
