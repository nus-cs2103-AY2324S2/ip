package xiaobai;

import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for XiaoBai using FXML.
 */
public class Main extends Application {

    private XiaoBai xiaoBai = new XiaoBai();

    /**
     * Starts the application by loading the main FXML file and initializing the
     * GUI.
     *
     * @param stage The primary stage for this application, onto which the
     *              application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setXiaoBai(xiaoBai);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
