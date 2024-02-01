package duke.ui;

import java.util.Scanner;

import duke.command.Command;
import duke.command.CommandProcessor;
import duke.exceptions.HalException;
import duke.storage.Storage;

/**
 * The `UserInterface` class handles user interactions and serves as the main interface for the Duke application.
 */
public class UserInterface {

    private Scanner scan;
    private CommandProcessor cmd;

    private boolean startUpSuccess = false;

    /**
     * Initializes a new `UserInterface` object, sets up a scanner for user input,
     * and attempts to start the Duke application.
     */
    public UserInterface() {
        try {
            scan = new Scanner(System.in);
            String fileLocation = "./savefile.txt";
            Storage storage = new Storage(fileLocation);
            cmd = new CommandProcessor(storage);
            startUpSuccess = true;
        } catch (HalException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        String greeting = "Hi! My name is HAL9000";
        System.out.println(greeting);
    }

    /**
     * Displays a farewell message to the user.
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

    /**
     * Polls for user input and processes commands until the user decides to exit.
     */
    public void poll() {
        boolean polling = true;
        while (polling) {
            String input = scan.nextLine();
            try {
                Command command = Command.processCommand(input);
                if (command.isExit()) {
                    polling = false;
                } else {
                    cmd.processData(command, input);
                }
            } catch (HalException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Starts the Duke application by displaying a greeting, polling for user input, and then exiting.
     */
    public void start() {

        if (!startUpSuccess) {
            startUpFailure();
            return;
        }

        greet();
        poll();
        exit();
    }
}
