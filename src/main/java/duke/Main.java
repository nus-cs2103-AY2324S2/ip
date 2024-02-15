package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            stage.setResizable(false);
            // Start a background thread to continuously check the exit condition
            checkAndExit().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a thread to check the exit status
     * and if it is true, exit the program.
     *
     * @return an ArrayList of task.
     */
    public Thread checkAndExit() {
        return new Thread(() -> {
            while (true) {
                // Check the exit condition in real-time
                if (duke.getIsExit()) {
                    // Terminate the Java application
                    System.exit(0);
                }
                try {
                    // Wait for a short period before checking again
                    Thread.sleep(4000); // Adjust the sleep time as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

