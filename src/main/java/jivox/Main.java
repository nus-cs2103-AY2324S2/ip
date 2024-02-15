package jivox;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Jivox jivox = new Jivox("./data/jivox.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("Jivox - The ChatBot!");
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(jivox);
            stage.setOnCloseRequest(event -> {
                this.jivox.saveDb();
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
