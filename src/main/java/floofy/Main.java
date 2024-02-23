package floofy;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Floofy using FXML.
 */
public class Main extends Application {

    /** The Floofy application. */
    private Floofy floofy = new Floofy();

    /**
     * Starts the chat-bot while bridging the gap between the GUI and the chat-bot.
     *
     * @param stage The stage to start the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setFloofy(floofy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
