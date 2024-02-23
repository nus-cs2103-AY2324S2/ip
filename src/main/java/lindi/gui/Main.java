package lindi.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lindi.Lindi;

/**
 * A GUI for Lindi using FXML.
 */
public class Main extends Application {

    private Lindi lindi = new Lindi();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setLindi(lindi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
