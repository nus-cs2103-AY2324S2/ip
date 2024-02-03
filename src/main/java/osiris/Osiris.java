package osiris;

import java.util.Scanner;

import osiris.commands.Command;
import osiris.interpreters.UserInputInterpreter;
import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * The Osiris class represents the main chatBot application.
 * It manages user interactions, interprets commands, and handles task management.
 */
public class Osiris {

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

}
