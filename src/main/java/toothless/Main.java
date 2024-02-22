package toothless;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import toothless.ui.MainWindow;

/**
 * A GUI for Toothless using FXML.
 */
public class Main extends Application {

    private Toothless toothless = new Toothless();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Toothless");
            fxmlLoader.<MainWindow>getController().setToothless(toothless);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
