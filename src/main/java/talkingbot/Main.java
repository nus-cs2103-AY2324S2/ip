package talkingbot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import talkingbot.gui.Window;
import talkingbot.logic.TalkingBot;

public class Main extends Application {
    private static final String PATH_TO_WINDOW_FXML = "/gui/Window.fxml";
    private TalkingBot talkingBot = new TalkingBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(PATH_TO_WINDOW_FXML));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<Window>getController().setBot(this.talkingBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
