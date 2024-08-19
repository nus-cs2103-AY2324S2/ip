package pan;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pan.controllers.MainWindow;

/**
 * Main - Represents the Main Class that stages the scene using FXML.
 * @author Jerome Goh
 */
public class Main extends Application {

    private Pan pan = new Pan(new Ui(), new TaskList(new Storage()));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPan(pan);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
