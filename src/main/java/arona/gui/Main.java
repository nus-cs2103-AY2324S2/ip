package arona.gui;

import java.io.IOException;

import arona.Arona;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Arona using FXML.
 */
public class Main extends Application {

    private Arona arona = new Arona();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            arona.loadTaskList();
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setArona(arona);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.setTitle("A.R.O.N.A.");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}