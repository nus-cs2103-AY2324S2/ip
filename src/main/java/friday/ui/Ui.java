package friday.ui;

import java.util.Scanner;

import friday.task.TaskList;

/**
 * Represents the user interface of the Friday application.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object with a Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns user input from the console.
     *
     * @return The user input as a String.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays the tasks in the task list.
     *
     * @param tasks The TaskList containing tasks to be displayed.
     */
    public void displayTaskList(TaskList tasks) {
        for (int i = 0; i < tasks.getLength(); i++) {
            System.out.println(i + 1 + ". " + tasks.getTask(i).toString());
        }
    }
}
