package jivox;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Jivox using FXML and JavaFX.
 */
public class Main extends Application {

    /**
     * Jivox Bot Instance to be Excuted
     */
    private Jivox jivox = new Jivox("./data/jivox.txt");

    /**
     * Entry point for the GUI of the Application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("Jivox - The ChatBot!");
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJivox(jivox);
            stage.setResizable(false);
            stage.setOnCloseRequest(event -> {
                this.jivox.saveDb();
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
