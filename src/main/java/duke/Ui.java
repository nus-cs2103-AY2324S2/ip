package duke;

import duke.exceptions.DukeException;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Class to deal with interactions with the user.
 *
 * This class requests input from the user and also show the status of their transaction through messages.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("Hello and welcome! I'm fakegpt!");

    }

    public void showBye() {
        System.out.println("Bye bye! Thanks for using me! I will remember the tasks you have to do!");
    }

    /**
     * Displays the list to user
     */
    public void showList(TaskList list) {
        System.out.println(list);
    }

    public void showLine() {
        System.out.println("-----------------------------------------------");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Returns next input from user
     *
     * @return a string from user input in CLI
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints out a message describing error encountered.
     *
     * @param e duke.exceptions.DukeException object
     */
    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

}
