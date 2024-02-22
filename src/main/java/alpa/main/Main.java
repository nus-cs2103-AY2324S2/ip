package alpa.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Alpa using FXML.
 */
public class Main extends Application {

    private Alpa alpa = new Alpa();

    /**
     * Starts the application.
     *
     * @param stage The stage to start the application in.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAlpa(alpa);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
