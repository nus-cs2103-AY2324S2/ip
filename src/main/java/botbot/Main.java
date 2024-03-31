package botbot;

import java.io.IOException;

import botbot.exception.BotBotException;
import botbot.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application{

    private BotBot botbot = new BotBot();

    public Main() throws BotBotException, IOException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(botbot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}