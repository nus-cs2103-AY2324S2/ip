package ChatbotRan;

import ChatbotRan.components.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Ran using FXML.
 */
public class RanApplication extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Ran ran = new Ran(new TaskIo("data/ran.txt"), new RanUi());
            FXMLLoader fxmlLoader = new FXMLLoader(RanApplication.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRan(ran);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}