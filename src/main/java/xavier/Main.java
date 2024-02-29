package xavier;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Xavier xavier = new Xavier("./data/data.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            
            stage.setScene(scene);
            stage.setTitle("I'm Xavier, serving your's truly :)");
            fxmlLoader.<MainWindow>getController().setXavier(xavier);
            fxmlLoader.<MainWindow>getController().showWelcome();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exit(boolean isExit) {
        if (isExit) {
            try {
                Thread.sleep(1500);
                Platform.exit();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
