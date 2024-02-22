package ping;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ping.exceptions.PingException;
import ping.gui.MainWindow;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String BOT_NAME = "Ping ChatBot";

    private Ping ping = new Ping();

    public Main() throws PingException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle(BOT_NAME);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPing(ping);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
