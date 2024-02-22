package sylvia;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sylvia.configuration.Info;
import sylvia.state.UserChatHistory;
import sylvia.ui.MainWindow;
import sylvia.ui.UI;

/**
 * The main class to start the bot.
 */
public class Main extends Application {
    private UI ui;
    private Sylvia sylvia;
    private UserChatHistory chatHistory;

    private void setUpStage(Stage stage, FXMLLoader fxmlLoader) throws IOException {
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.setTitle(Info.CHATBOT_NAME);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/image/icon.jpg")));
        stage.show();
    }

    private void setUpBot(MainWindow mw) {
        chatHistory = new UserChatHistory();
        ui = new UI(mw);
        sylvia = new Sylvia(ui);

        // set up action handlers
        mw.setInputHandler((String s) -> {
            chatHistory.addChatToHistory(s);
            return sylvia.runCommand(s);
        });
        mw.setChatHistorySuppliers(chatHistory::getPreviousChat, chatHistory::getNextChat);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            // set up the stage
            setUpStage(stage, fxmlLoader);

            // set up the UI controller and start the bot
            setUpBot(fxmlLoader.<MainWindow>getController());

            ui.showWelcomeMessage();
        } catch (Exception e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    @Override
    public void stop() {
        sylvia.exit();
    }
}
