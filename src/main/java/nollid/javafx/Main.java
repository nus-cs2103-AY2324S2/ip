package nollid.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nollid.Nollid;
import nollid.Storage;

/**
 * Main class responsible for launching the Nollid application.
 */
public class Main extends Application {
    private Nollid nollid = new Nollid(Storage.DEFAULT_FILEPATH);

    /**
     * The entry point for the application.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            assert nollid != null : "nollid instance should not be null";
            fxmlLoader.<MainWindow>getController().setNollid(nollid);

            stage.setTitle("Nollid");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
