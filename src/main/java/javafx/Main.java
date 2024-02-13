package javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.RyanGosling;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private RyanGosling ryanGosling = new RyanGosling();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("RyanGosling Bot");
            fxmlLoader.<MainWindow>getController().setRyanGosling(ryanGosling);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
