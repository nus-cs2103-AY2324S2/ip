package chatbot;

import java.util.Scanner;

import chatbot.action.Action;
import chatbot.action.ByeAction;
import chatbot.action.exception.ActionException;
import chatbot.parse.InputParser;
import chatbot.storage.LocalStorage;
import chatbot.task.TaskList;
import chatbot.ui.Printer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This encapsulates the behaviour of a chatbot,
 * which is the handling of the message content and executing commands.
 *
 * @author Titus Chew
 */
public class ChatBot extends Application {
    /** Stores the scanner instance used to get the console input stream. */
    private static final Scanner SCANNER = new Scanner(System.in);

    /** Stores the name of this. */
    private static final String CHATBOT_NAME = "Stratify";

    /** Stores the user's tasks. */
    private static final TaskList USER_TASK_LIST = LocalStorage.loadTaskList();

    /**
     * Creates the JavaFX Application instance by calling the no-argument class constructor.
     */
    public ChatBot() {
        run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Greets the user when entering a session with this {@link ChatBot}.
     */
    private void greet() {
        Printer.printMessages(
                "Hello! I'm " + CHATBOT_NAME + "!",
                "What can I do for you?"
        );
    }

    /**
     * Runs the chatbot main loop.
     */
    public void run() {
        greet();

        Action userAction = null;
        do {
            try {
                userAction = InputParser.getParsedInput(SCANNER.nextLine());
                userAction.execute(USER_TASK_LIST);
            } catch (ActionException e) {
                Printer.printMessages(e.getMessage());
            }
        } while (!(userAction instanceof ByeAction));
    }
}
