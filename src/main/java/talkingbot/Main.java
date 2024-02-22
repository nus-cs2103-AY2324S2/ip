package talkingbot;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import talkingbot.gui.Window;
import talkingbot.logic.TalkingBot;

/**
 * Class connecting the logic and the GUI of the application.
 */
public class Main extends Application {
    private static final String PATH_TO_WINDOW_FXML = "/gui/Window.fxml";
    private static final int CHECK_INTERVAL = 100;
    private TalkingBot talkingBot = new TalkingBot();
    private Thread stopChecker;

    /**
     * Loads the main window of the application and displays it.
     *
     * @param stage Stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(PATH_TO_WINDOW_FXML));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<Window>getController().setBot(this.talkingBot);
            stage.show();
            stage.setTitle("TalkingBot");
            this.startChecker();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts a new Thread that checks whether the bot should
     * still remain running.
     */
    // Solution below adapted from https://github.com/GERARDJM018/ip/blob/master/src/main/java/duke/Main.java
    private void startChecker() {
        this.stopChecker = new Thread(() -> {
            while (true) {
                if (this.talkingBot.getIsRunning()) {
                    try {
                        Thread.sleep(CHECK_INTERVAL);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    continue;
                }
                try {
                    this.stop();
                    this.stopChecker.sleep(5 * CHECK_INTERVAL);
                    Platform.exit();
                    this.stopChecker.stop();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            }
        });
        this.stopChecker.start();
    }

    /**
     * Stops the running of the program.
     *
     * @throws Exception If there are any errors.
     */
    @Override
    public void stop() throws Exception {
        this.talkingBot.save();
        super.stop();
    }
}
