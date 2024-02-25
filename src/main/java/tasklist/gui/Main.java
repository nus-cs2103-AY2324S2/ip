package tasklist.gui;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tasklist.Michelle;

/**
 * A GUI for Michelle using FXML.
 */
public class Main extends Application {

    private Michelle michelle = new Michelle();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("MichelleBot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMichelle(michelle);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
