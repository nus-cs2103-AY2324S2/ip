package sleepy;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sleepy.gui.MainWindow;

/**
 * Main class for the Sleepy AI chatbot.
 *
 * @author kjw142857
 */
public class Main extends Application {
    private Sleepy sleepy = new Sleepy();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Sleepy - your tired AI chatbot");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            HBox hb = fxmlLoader.load();
            Scene scene = new Scene(hb);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSleepy(sleepy);
            stage.show();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
