package duke;

import duke.exceptions.DukeException;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Class to deal with interactions with the user.
 * This class requests input from the user and also shows the status of their transaction through messages.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /** Displays welcome message */
    public void showWelcome() {
        System.out.println("Hello and welcome! I'm fakegpt!");
    }

    /** Displays bye message */
    public void showBye() {
        System.out.println("Bye bye! Thanks for using me! I will remember the tasks you have to do!");
    }

    /** Displays the list to user */
    public void showList(TaskList list) {
        System.out.println(list);
    }

    /** Displays a line break to user */
    public void showLine() {
        System.out.println("-----------------------------------------------");
    }

    /**
     * Displays given message to user.
     *
     * @param message String to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads the next input from user.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a message describing error encountered.
     *
     * @param e DukeException object.
     */
    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

}
