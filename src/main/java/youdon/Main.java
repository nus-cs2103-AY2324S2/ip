package youdon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main class responsible for launching the YoudonBot GUI application.
 */
public class Main extends Application {

    private final Youdon youdon = new Youdon();

    /**
     * Start method called when launching the application.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("YoudonBot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setYoudon(youdon);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}