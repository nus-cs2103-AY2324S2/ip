package edgar;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A JavaFX application class representing the GUI for Edgar using FXML.
 */
public class Main extends Application {

    private EdgarChatBot edgar = new EdgarChatBot();

    /**
     * The main entry point for the JavaFX application.
     *
     * @param stage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("EdgarChatBot");
            fxmlLoader.<MainWindow>getController().setEdgarChatBot(edgar);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
