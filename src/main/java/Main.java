import snoopy.Snoopy;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.MainWindow;

/**
 * A GUI for Snoopy using FXML.
 */
public class Main extends Application {

    private Snoopy snoopy = new Snoopy();

    /**
     * Starts the GUI for Snoopy.
     * @param stage the stage for the GUI
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("SnoopyBot");
            fxmlLoader.<MainWindow>getController().setSnoopy(snoopy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
