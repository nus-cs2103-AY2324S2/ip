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
    public String showWelcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello! I'm AcademicWeapon\n");
        sb.append("What can I do for you?\n");
        System.out.println("Hello! I'm AcademicWeapon\n");
        System.out.println("What can I do for you?\n");
        Ui.showLine();
        return (sb.toString());
    }

    /**
     * Displays a horizontal line as a separator in the console.
     */
    public static String showLine() {
        StringBuilder sb = new StringBuilder();
        System.out.println("____________________________________________________________");
        sb.append("____________________________________________________________\n");
        return sb.toString();
    }

    /**
     * Displays an error message when there is an issue loading a file.
     */
    public String showLoadingError() {
        StringBuilder sb = new StringBuilder();
        System.out.println("Error loading file.");
        sb.append("Error loading file.\n");
        return sb.toString();
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
    public String printMarkDone(Task markTask) {
        StringBuilder sb = new StringBuilder();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(markTask.toString());
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(markTask.toString());
        return sb.toString();
    }

    /**
     * Prints a message indicating that a task has been masked as not done.
     *
     * @param unmarkTask The task that has been marks as not done
     */
    public String printUnmark(Task unmarkTask) {
        StringBuilder sb = new StringBuilder();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(unmarkTask.toString());
        sb.append("OK, I've marked this task as not done yet:\n");
        sb.append(unmarkTask.toString());
        return sb.toString();
    }

    /**
     * Prints a message indicating that a task has been successfully added
     *
     * @param addTask The task that has been added
     * @param size The current size of the task list
     */
    public String printAddSuccessful(Task addTask, int size) {
        StringBuilder sb = new StringBuilder();
        System.out.println("Got it. I've added this task:");
        System.out.println(addTask.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        sb.append("Got it. I've added this task:\n");
        sb.append(addTask.toString());
        sb.append("Now you have " + size + " tasks in the list.\n");
        return sb.toString();
    }

    /**
     * Prints a message indicating that a task has been successfully removed.
     *
     * @param toBeRemoved The task that has been removed
     * @param size The current size of the task list
     */
    public String removeSuccessful(Task toBeRemoved, int size) {
        StringBuilder sb = new StringBuilder();
        System.out.println("Noted. I've removed this task:");
        System.out.println(toBeRemoved.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        sb.append("Noted. I've removed this task:\n");
        sb.append(toBeRemoved.toString());
        sb.append("Now you have " + size + " tasks in the list.\n");
        return sb.toString();
    }

    /**
     * Displays a goodbye message when the application is about to exit.
     */
    public String showBye() {
        StringBuilder sb = new StringBuilder();
        System.out.println("Bye. Hope to see you again soon!");
        sb.append("Bye. Hope to see you again soon!\n");
        return sb.toString();
    }
}
