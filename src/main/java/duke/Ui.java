package duke;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new Ui instance, initializing the scanner to read from standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Lucifer\nWhat can I do for you?");
        System.out.println("______________________________________________________");
    }


    /**
     * Displays a task to the user.
     *
     * @param task The string representation of the task to be displayed.
     */
    public void showTask(String task) {
        System.out.println(" " + task);
    }

}
