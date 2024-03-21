package gui;

import java.io.IOException;

import balkan.BalkanBot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private BalkanBot balkanBot = new BalkanBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Balkan Bot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBalkanBot(balkanBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
