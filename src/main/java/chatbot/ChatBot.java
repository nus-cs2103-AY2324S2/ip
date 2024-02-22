package chatbot;

import chatbot.action.Action;
import chatbot.action.ByeAction;
import chatbot.action.ModifyAction;
import chatbot.action.exception.ActionException;
import chatbot.parse.InputParser;
import chatbot.print.Message;
import chatbot.print.PrintFormatter;
import chatbot.storage.LocalStorage;
import chatbot.storage.SaveState;
import chatbot.task.TaskList;
import chatbot.ui.MainWindow;
import javafx.application.Application;
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

    /** Store the main window of the chatbot. */
    private static final MainWindow MAIN_WINDOW = new MainWindow();

    /**
     * Setups the JavaFX application.
     * <p>
     * Reused from {@code https://se-education.org/guides/tutorials/javaFx.html}, with minor modifications.
     */
    @Override
    public void start(Stage stage) {
        MAIN_WINDOW.setUpStage(stage);
        MAIN_WINDOW.formatWindow(stage, CHATBOT_NAME);
        MAIN_WINDOW.handleUserUpdate(this);
        MAIN_WINDOW.getStartUpMessages(this);
    }

    /**
     * Gets the response message given a command line input string from the user.
     * This will close the application if the command is to end the chat.
     * <p>
     * Reused with changes from {@code https://se-education.org/guides/tutorials/javaFx.html}.
     */
    public Message[] getResponseMessages(String commandLineInput) {
        try {
            executeCommand(commandLineInput);
        } catch (ActionException e) {
            PrintFormatter.addErrorToMessageQueue(e.getMessage());
        }

        return PrintFormatter.getAllMessages();
    }

    /**
     * Executes the action to execute from the command line input,
     * writing to the {@link PrintFormatter}.
     *
     * @throws ActionException If the input is invalid.
     */
    private void executeCommand(String commandLineInput) throws ActionException {
        Action userAction = InputParser.getParsedInput(commandLineInput);

        userAction.execute(USER_TASK_LIST);

        if (userAction instanceof ModifyAction) {
            SaveState.saveCurrentState(USER_TASK_LIST.saveState(), commandLineInput);
        }

        if (userAction instanceof ByeAction) {
            PrintFormatter.addLogToMessageQueue("Chat has ended. Close the chat to exit.");
            MAIN_WINDOW.endChat();
        }
    }

    /**
     * Greets the user when entering a session with this {@link ChatBot}.
     */
    public void greetUser() {
        PrintFormatter.addToMessageQueue(
                "Hello! I'm " + CHATBOT_NAME + "!",
                "What can I do for you?"
        );
    }
}
