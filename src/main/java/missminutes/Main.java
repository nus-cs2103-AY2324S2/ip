package missminutes;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for MissMinutes using FXML.
 */
public class Main extends Application {

    private final MissMinutes mm = new MissMinutes();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMissMinutes(mm);
            stage.setTitle("Miss Minutes Task Manager");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
