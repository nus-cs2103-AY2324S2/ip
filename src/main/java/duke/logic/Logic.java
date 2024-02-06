package duke.logic;

import java.util.Scanner;

import duke.command.Command;
import duke.command.CommandProcessor;
import duke.exceptions.HalException;
import duke.storage.Storage;
import javafx.application.Platform;

/**
 * The `UserInterface` class handles user interactions and serves as the main interface for the Duke application.
 */
public class Logic {

    private Scanner scan;
    private CommandProcessor cmd;
    private boolean startUpSuccess = false;

    /**
     * Initializes a new `UserInterface` object, sets up a scanner for user input,
     * and attempts to start the Duke application.
     */
    public Logic() {
        try {
            scan = new Scanner(System.in);
            String fileDirectory = "./data/";
            String fileName = "savefile.txt";
            Storage storage = new Storage(fileDirectory, fileName);
            cmd = new CommandProcessor(storage);
            startUpSuccess = true;
        } catch (HalException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays a farewell message to the user in CLI.
     */
    public void exit() {
        String exit = "Bye! See ya soon";
        System.out.println(exit);
        scan.close();
        cmd.close();
    }

    /**
     * Displays a message indicating a startup failure to the user.
     */
    public void startUpFailure() {
        System.out.println("Hi, you failed to start up properly! Sorry, bye!");
    }

    public String greet() {
        return "Hi Dave, I'm HAL9000. What can I do for you today?";
    }

    public String getResponse(String input) {
        if (!startUpSuccess) {
            startUpFailure();
            return "";
        }

        try {
            Command command = Command.processCommand(input);
            if (command.isExit()) {
                exit();
                Platform.exit();
                return "";
            } else {
                return cmd.processData(command, input);
            }
        } catch (HalException e) {
            return e.getMessage();
        }
    }
}
