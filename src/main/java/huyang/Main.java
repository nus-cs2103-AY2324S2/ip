package huyang;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Huyang using FXML.
 */
public class Main extends Application {

    private Huyang huyang = new Huyang();

    /**
     * The main entry point of the JavaFX application.
     *
     * @param stage The primary stage for the JavaFX application, where the GUI will be displayed.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHuyang(huyang);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
