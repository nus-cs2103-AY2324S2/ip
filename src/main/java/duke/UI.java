package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents the user interface of Duke.
 */
public class UI {
    private static final String LINE = "_".repeat(60);
    private static final ArrayList<String> WELCOME_MESSAGES = new ArrayList<String>(
            Arrays.asList("Hello! I'm Echon", "What can I do for you?"));
    private static final int LINE_INDENTATION = 4;
    private static final int CONTENT_INDENTATION = 5;

    private boolean isActive = true;

    public UI() {
    }

    /**
     * Prints a message to the user.
     * 
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(" ".repeat(LINE_INDENTATION) + LINE);
        System.out.println(" ".repeat(CONTENT_INDENTATION) + message);
        System.out.println(" ".repeat(LINE_INDENTATION) + LINE);
        System.out.println("");
    }

    /**
     * Prints a multi-line message to the user.
     * 
     * @param messages The list of message lines to be printed.
     */
    public void printMessages(ArrayList<String> messages) {
        System.out.println(" ".repeat(LINE_INDENTATION) + LINE);
        for (String message : messages) {
            System.out.println(" ".repeat(CONTENT_INDENTATION) + message);
        }
        System.out.println(" ".repeat(LINE_INDENTATION) + LINE);
        System.out.println("");
    }

    /**
     * Prints the welcome message to the user.
     */
    public void printWelcomeMessage() {
        printMessages(WELCOME_MESSAGES);
    }
    
    /**
     * Processes the commands entered by the user.
     * 
     * @param commandCreator The command creator to create commands.
     * @param storage The storage to save the task list.
     */
    public void processCommands(CommandCreator commandCreator, Storage storage) {
        Scanner scanner = new Scanner(System.in);
        while (this.isActive) {
            String input = scanner.nextLine();
            try {
                Command command = commandCreator.createCommand(input);
                command.execute(this);
                storage.save();
            } catch (DukeException e) {
                printMessage(e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Sets whether the user interface is active.
     * Setting the user interface to inactive will cause the user interface to terminate.
     * 
     * @param isActive Whether the user interface is active.
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
