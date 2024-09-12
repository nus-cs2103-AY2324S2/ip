package ganAnWo;

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

    private GanAnWo ganAnWo = new GanAnWo();

    @Override
    public void start(Stage stage) {
        assert ganAnWo != null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("GanAnWo ChatBot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(ganAnWo);
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
     * @return a thread that checks the exit status.
     */
    public Thread checkAndExit() {
        return new Thread(() -> {
            while (true) {
                if (ganAnWo.getIsExit()) {
                    System.exit(0);
                }
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

