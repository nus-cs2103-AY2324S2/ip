package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import wei.Wei;

/**
 * A GUI for Wei using FXML.
 */
public class Main extends Application {

    private Wei wei = new Wei();

    /**
     * Generates the GUI.
     *
     * @param stage the primary stage for this application, onto which
     *      the application scene can be set.
     *      Applications may create other stages, if needed, but they will not be
     *      primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setWei(wei);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
