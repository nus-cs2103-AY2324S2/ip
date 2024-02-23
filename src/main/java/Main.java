import java.io.IOException;

import signal.Signal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Signal using FXML.
 */
public class Main extends Application {

    private Signal signal = new Signal();


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(true);
            fxmlLoader.<GUI.MainWindow>getController().setDuke(signal);
            stage.show();

            fxmlLoader.<GUI.MainWindow>getController().handleSignalIntro();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
