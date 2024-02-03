package academicweapon.ui;

import academicweapon.exceptions.DukeExceptions;
import academicweapon.task.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The Ui class handles the user interface
 */
public class Ui {
    /**
     * Displays a welcome message when the application starts.
     */
    public void showWelcome() {
        Ui.showLine();
        System.out.println("Hello! I'm AcademicWeapon");
        System.out.println("What can I do for you?");
        Ui.showLine();
    }

    /**
     * Displays a horizontal line as a separator in the console.
     */
    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message when there is an issue loading a file.
     */
    public void showLoadingError() {
        Ui.showLine();
        System.out.println("Error loading file.");
        Ui.showLine();
    }

    /**
     * Reads a user command from the console.
     *
     * @return The user command as a string
     * @throws IOException If an I/O error occurs while reading the command
     * @throws DukeExceptions If there is an issue with the command
     */
    public String readCommand() throws IOException, DukeExceptions {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        return input;
    }

    /**
     * Prints a message indicating that a task has been marks as done.
     *
     * @param markTask The task that has been marked as done.
     */
    public void printMarkDone(Task markTask) {
        Ui.showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(markTask.toString());
        Ui.showLine();
    }

    /**
     * Prints a message indicating that a task has been masked as not done.
     *
     * @param unmarkTask The task that has been marks as not done
     */
    public void printUnmark(Task unmarkTask) {
        Ui.showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(unmarkTask.toString());
        Ui.showLine();
    }

    /**
     * Prints a message indicating that a task has been successfully added
     *
     * @param addTask The task that has been added
     * @param size The current size of the task list
     */
    public void printAddSuccessful(Task addTask, int size) {
        Ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(addTask.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        Ui.showLine();
    }

    /**
     * Prints a message indicating that a task has been successfully removed.
     *
     * @param toBeRemoved The task that has been removed
     * @param size The current size of the task list
     */
    public void removeSuccessful(Task toBeRemoved, int size) {
        Ui.showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(toBeRemoved.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        Ui.showLine();
    }

    /**
     * Displays a goodbye message when the application is about to exit.
     */
    public void showBye() {
        Ui.showLine();
        System.out.println("Bye. Hope to see you again soon!");
        Ui.showLine();
    }
}
