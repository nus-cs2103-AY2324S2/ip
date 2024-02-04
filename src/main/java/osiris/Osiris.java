package osiris;

import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import osiris.commands.Command;
import osiris.interpreters.UserInputInterpreter;
import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * The Osiris class represents the main chatBot application.
 * It manages user interactions, interprets commands, and handles task management.
 */
public class Osiris {

    private ScrollPane dialogScrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image osiris = new Image(this.getClass().getResourceAsStream("/images/osiris.png"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));

    /** Represents the task manager responsible for managing tasks within the system. */
    private final TaskManager taskManager = new TaskManager();

    /** Represents the user interface component of the application. */
    private final Ui userInterface = new Ui();

    /**
     * Initiates the chat session with the user.
     * Manages user input, interprets commands, and handles task management.
     */
    public void startChat() {
        Scanner scanner = new Scanner(System.in);
        taskManager.initialise();

        userInterface.displayIntroductions();

        boolean isTerminate = false;

        while (!isTerminate) {
            userInterface.displayOsirisPromptMessage();
            String userInput = scanner.nextLine();

            Command userCommand = UserInputInterpreter.getInstance().interpretUserInput(userInput);
            userCommand.execute(taskManager, userInterface);
            isTerminate = userCommand.isTerminateChat();
        }

        userInterface.displayGoodbyes();
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
