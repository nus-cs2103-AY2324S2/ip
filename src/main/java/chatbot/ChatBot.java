package chatbot;

import chatbot.action.Action;
import chatbot.action.ByeAction;
import chatbot.action.exception.ActionException;
import chatbot.parse.InputParser;
import chatbot.storage.LocalStorage;
import chatbot.task.TaskList;
import chatbot.ui.MainWindow;
import chatbot.ui.PrintFormatter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * This encapsulates the behaviour of a chatbot,
 * which is the handling of the message content and executing commands.
 *
 * @author Titus Chew
 */
public class ChatBot extends Application {
    /** Stores the name of this. */
    private static final String CHATBOT_NAME = "Stratify";

    /** Stores the user's tasks. */
    private static final TaskList USER_TASK_LIST = LocalStorage.loadTaskList();

    /**
     * Setups the JavaFX application.
     * <p>
     * Reused from {@code https://se-education.org/guides/tutorials/javaFx.html}, with minor modifications.
     */
    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setUpStage(stage);
        mainWindow.formatWindow(stage, CHATBOT_NAME);
        mainWindow.handleUserUpdate(this);

        greetUser();
        mainWindow.getChatBotMessages();
    }

    /**
     * Gets the response message given a command line input string from the user.
     * This will close the application if the command is to end the chat.
     * <p>
     * Reused with changes from {@code https://se-education.org/guides/tutorials/javaFx.html}.
     */
    public String getResponseMessage(String commandLineInput) {
        try {
            Action userAction = InputParser.getParsedInput(commandLineInput);

            if (userAction instanceof ByeAction) {
                Platform.exit();
            }

            return userAction.execute(USER_TASK_LIST);
        } catch (ActionException e) {
            return PrintFormatter.formatMessages(e.getMessage());
        }
    }

    /**
     * Greets the user when entering a session with this {@link ChatBot}.
     */
    public void greetUser() {
        PrintFormatter.addToFormatterQueue(
                "Hello! I'm " + CHATBOT_NAME + "!",
                "What can I do for you?"
        );
    }
}
