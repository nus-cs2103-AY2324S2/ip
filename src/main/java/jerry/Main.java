package jerry;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Jerry using FXML.
 */
public class Main extends Application {
    private final Jerry jerry = new Jerry();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Jerry");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJerry(jerry);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

