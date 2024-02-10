import javafx.application.Application;
import javafx.stage.Stage;
import parser.Parser;
import task.TaskStorage;
import ui.ChatManager;
import ui.UiManager;
import util.DataReader;
import util.TextUi;

/**
 * The main user interface for the "ChatBot", promptly named BobBot.
 *
 * This class is a front to manage user input, displaying the corresponding output,
 * and managed the conditional statements for the prompting.
**/
public class Duke extends Application {
    @Override
    public void start(Stage stage) {
        TaskStorage taskStorage = new DataReader().readDataFile(new TextUi());
        Parser parser = new Parser();
        UiManager uiManager = new UiManager(stage);
        ChatManager chatController = new ChatManager(uiManager, parser, taskStorage);

        uiManager.setupUI();
        chatController.setupHandlers();
    }

    /**
     * Main function to start the Chatbot.
     *
     * @param args arguments for the method.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
