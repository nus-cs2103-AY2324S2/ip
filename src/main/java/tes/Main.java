package tes;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tes.gui.MainWindow;

/**
 * A GUI for Tes using FXML.
 */
public class Main extends Application {

    private Tes tes = new Tes();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTes(tes);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
