import java.io.IOException;

import gui.MainWindow;
import howie.Howie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Howie using FXML.
 */
public class Main extends Application {

    private Howie howie = new Howie();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Howie");
            fxmlLoader.<MainWindow>getController().setHowie(howie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
