package felix;

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
public class Main extends Application {

    private Felix felix = new Felix();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle("Felix");
            fxmlLoader.<MainWindow>getController().setFelix(felix);
            fxmlLoader.<MainWindow>getController().helloFelix();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
