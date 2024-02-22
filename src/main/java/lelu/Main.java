package lelu;
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
 * A GUI for Lelu using FXML.
 */
public class Main extends Application {

    private Lelu lelu = new Lelu();
    private static final int exitDelay = 3000;

    public static void exitApplication() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(1);
            }
        }, exitDelay);
    }



    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<ui.MainWindow>getController().setLelu(lelu);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

