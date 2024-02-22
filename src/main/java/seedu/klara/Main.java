package seedu.klara;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Klara using FXML.
 */
public class Main extends Application {

    private Klara klara = new Klara();

    @Override
    public void start(Stage stage) {
        assert klara != null : "Klara should not be null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKlara(klara);
            fxmlLoader.<MainWindow>getController().showWelcomeMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
