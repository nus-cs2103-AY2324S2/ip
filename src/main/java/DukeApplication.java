import java.io.IOException;

import gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class DukeApplication extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                DukeApplication.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            fxmlLoader.<MainWindow>getController();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
