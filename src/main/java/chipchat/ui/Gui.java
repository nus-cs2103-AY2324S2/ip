package chipchat.ui;

import java.beans.EventHandler;
import java.io.IOException;

import chipchat.App;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * A GUI for Duke using FXML.
 */
public class Gui extends Application {

    private App app;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("ChipChat");
            this.app = new App(stage);
            fxmlLoader.<MainWindow>getController().setApp(app);
            fxmlLoader.<MainWindow>getController().showInitialGreeting();
            stage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(0);
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
