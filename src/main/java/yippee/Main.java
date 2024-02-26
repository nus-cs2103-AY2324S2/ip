package yippee;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import GUI.MainWindow;
/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Yippee yippee = new Yippee();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            yippee.start(stage);
            fxmlLoader.<MainWindow>getController().setYippee(yippee);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
