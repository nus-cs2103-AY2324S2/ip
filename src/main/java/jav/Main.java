package jav;

import java.io.IOException;

import jav.frontend.MainWindow;
import jav.manager.FileManager;
import jav.manager.StorageManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Jav using FXML.
 */
public class Main extends Application {

    private Jav jav = new Jav();

    @Override
    public void start(Stage stage) {
        try {
            StorageManager.getInstance().load(FileManager.getInstance().loadStorageData());

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Joy Amplifying Virtuoso");
            fxmlLoader.<MainWindow>getController().setJav(jav);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
