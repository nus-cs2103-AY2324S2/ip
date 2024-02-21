package rochin;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private RochinBot rochin = new RochinBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            MainWindow mainWindow = fxmlLoader.getController();
            RochinBot rochin = new RochinBot();
            mainWindow.setRochin(rochin);
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("RochinBot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

