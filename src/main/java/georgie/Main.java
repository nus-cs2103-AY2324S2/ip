package georgie;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Georgie using FXML.
 */
public class Main extends Application {

    private Georgie georgie = new Georgie();

    /**
     * Exits the application after a delay of 3 seconds.
     */
    public static void exitApp() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, 3000);
    }

    /**
     * Starts the JavaFX application by loading the FXML file and setting up the scene.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGeorgie(georgie);
            stage.setTitle("Georgie");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

