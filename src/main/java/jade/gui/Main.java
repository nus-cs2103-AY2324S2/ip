package jade.gui;

import java.io.IOException;

import jade.Jade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Jade using FXML.
 */
public class Main extends Application {
    private Jade jade = new Jade();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJade(jade);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
