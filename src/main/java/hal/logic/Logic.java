package hal.logic;

import hal.command.Command;
import hal.command.CommandProcessor;
import hal.exceptions.HalException;
import hal.history.HistoryManager;
import hal.history.State;
import hal.storage.Storage;
import javafx.application.Platform;

/**
 * The `Logic` class handles user interactions and serves as the main logical interface
 * for the HAL9000 application.
 */
public class Logic {
    private static final String DATA_FILE_DIRECTORY = "./data/";
    private static final String SAVE_FILE_NAME = "savefile.txt";
    private CommandProcessor cmd;
    private boolean isStartUpSuccess = false;

    /**
     * Initializes a new `Logic` object, sets up a scanner for user input,
     * and attempts to start the Duke application.
     */
    public Logic() {
        try {
            Storage storage = new Storage(DATA_FILE_DIRECTORY, SAVE_FILE_NAME);
            State startState = storage.getCurrState(Command.BYE);
            HistoryManager historyManager = new HistoryManager(startState);
            cmd = new CommandProcessor(storage, historyManager);
            isStartUpSuccess = true;
        } catch (HalException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays a farewell message to the user in CLI. Exits and closes the application.
     */
    public String exit() {
        String exit = "Bye! See ya soon";
        System.out.println(exit);
        cmd.close();
        Platform.exit();
        return "";
    }

    /**
     * Displays a message indicating a startup failure to the user.
     */
    public String startUpFailure() {
        System.out.println("Hi, you failed to start up properly! Sorry, bye!");
        return "Hi, you failed to start up properly! Sorry, please close the application";
    }

    public String greet() {
        return "Hi Dave, I'm HAL9000. What can I do for you today?";
    }

    /**
     * Processes and returns the response from the HAL9000 application to be displayed by the GUI
     * @param input The input string
     * @return A response from HAL9000
     */
    public String getResponse(String input) {
        if (!isStartUpSuccess) {
            return startUpFailure();
        }

        try {
            Command command = Command.processCommand(input);
            if (command.isExit()) {
                return exit();
            } else {
                return cmd.processData(command, input);
            }
        } catch (HalException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
