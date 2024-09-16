package harper;

import java.io.IOException;

import harper.frontend.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Harper harper = new Harper();

    @Override
    public void start(Stage stage) {
        assert this.harper != null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHarper(harper);
            stage.setTitle("Harper");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
