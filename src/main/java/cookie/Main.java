package cookie;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The entry point for the Cookie application GUI using FXML.
 */
public class Main extends Application {

    private Cookie cookie = new Cookie();

    /**
     * Starts the Cookie GUI application.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCookie(cookie);
            stage.setTitle("Cookie");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
