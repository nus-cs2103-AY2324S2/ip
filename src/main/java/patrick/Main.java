package patrick;

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

    private Patrick patrick = new Patrick();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("patrick");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPatrick(patrick);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
