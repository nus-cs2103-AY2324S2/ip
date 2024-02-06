package osiris;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import osiris.commands.Command;
import osiris.exceptions.OsirisException;
import osiris.exceptions.OsirisStorageFileException;
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
     * Initiates the necessary dependencies & outputs introductions when done.
     */
    public String startChat() {
        try {
            taskManager.initialise();
            return userInterface.displayIntroductions();
        } catch (OsirisStorageFileException e) {
            return e.getMessage() + " Please restart system.";
        } catch (OsirisException e) {
            return e.getMessage() + " Please restart system.";
        }
    }

    /**
     * Processes the user input.
     *
     * @param userInput The user input string.
     * @return String to be outputted to the user.
     */
    public String processInput(String userInput) {
        String response = "";
        boolean isTerminate = false;
        try {
            Command userCommand = UserInputInterpreter.getInstance().interpretUserInput(userInput);
            response = userCommand.execute(taskManager, userInterface);
            isTerminate = userCommand.isTerminateChat();
            if (isTerminate) {
                PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
                delay.setOnFinished(event -> {
                    Platform.exit();
                });
                delay.play();
            }
        } catch (OsirisException e) {
            return e.getMessage();
        }
        return response;
    }
}
