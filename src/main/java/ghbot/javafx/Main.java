package ghbot.javafx;

import java.io.IOException;

import ghbot.GhBot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main Class.
 * Prepares the GUI for GhBot.
 */
public class Main extends Application {
    private GhBot ghBot = new GhBot("./data/history.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("GhBot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGhBot(this.ghBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
