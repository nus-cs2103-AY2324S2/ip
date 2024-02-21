package bond.main;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bond using FXML.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class Main extends Application {

    private Bond bond;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Bond");
            stage.setResizable(false);

            {
                try {
                    bond = new Bond();
                } catch (BondException e) {
                    assert bond != null : "Bond object should be instantiated with an empty TaskList.";

                    fxmlLoader.<MainWindow>getController().tellUser(e.getMessage());
                }
            }

            fxmlLoader.<MainWindow>getController().setBond(bond);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows the application to terminate after a 'bye' command is processed.
     */
    protected static void exitApplication() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Delay for 2 second
        int delayInSeconds = 2;

        // Task to be executed after the delay
        Runnable delayedTask = () -> System.exit(0);

        // Schedule the task with the desired delay
        scheduler.schedule(delayedTask, delayInSeconds, TimeUnit.SECONDS);

        assert false : "Application should have exited.";
    }
}

