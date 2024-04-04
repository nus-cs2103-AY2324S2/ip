package anita;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Anita using FXML.
 */
public class Main extends Application {

    private Anita anita = new Anita();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("I Need The Max Win");
            fxmlLoader.<MainWindow>getController().setAnita(anita);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
