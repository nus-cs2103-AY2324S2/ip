package cruisey;

import java.io.IOException;

import cruisey.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Cruisey cruisey = new Cruisey();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Cruisey");
            stage.show();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(cruisey);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
