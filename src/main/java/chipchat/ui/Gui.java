package chipchat.ui;

import java.io.IOException;

import chipchat.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Gui extends Application {

    private App app = new App();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("ChipChat");
            fxmlLoader.<MainWindow>getController().setApp(app);
            fxmlLoader.<MainWindow>getController().showInitialGreeting();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
