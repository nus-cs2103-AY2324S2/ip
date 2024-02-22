import java.io.IOException;

import GUI.DialogBox;
import signal.Duke;

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
            stage.setResizable(true);
            fxmlLoader.<GUI.MainWindow>getController().setDuke(duke);
            stage.show();

            fxmlLoader.<GUI.MainWindow>getController().handleSignalIntro();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
