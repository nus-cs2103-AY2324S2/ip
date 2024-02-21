package luke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import luke.exception.SaveFileCorruptedException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Luke duke = new Luke();

    public Main() throws IOException, SaveFileCorruptedException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("Luke");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
