package lucky.ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import lucky.Lucky;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Lucky using FXML.
 */
public class Main extends Application {

    private final Lucky lucky = new Lucky();

    public Main() throws FileNotFoundException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLucky(lucky);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
