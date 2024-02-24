package raphael;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import raphael.gui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Raphael raphael = new Raphael(Raphael.mode.NORMAL);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(true);
            ap.resize(stage.getHeight(), stage.getWidth());
            fxmlLoader.<MainWindow>getController().setRaphael(this.raphael);
            stage.setTitle("Raphael");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

