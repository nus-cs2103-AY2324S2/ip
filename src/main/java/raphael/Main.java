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

    private final Raphael raphael = new Raphael(Raphael.Mode.NORMAL);

    @Override
    public void start(Stage stage) {
        try {
            stage.setResizable(false);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRaphael(this.raphael);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

