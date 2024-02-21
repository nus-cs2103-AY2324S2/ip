package riri;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Start the GUI for Riri using FXML.
 */
public class Main extends Application {
    private final Riri riri = new Riri();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/style/dialogBoxStyle.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRiri(riri);
            stage.show();
            fxmlLoader.<MainWindow>getController().welcome();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
