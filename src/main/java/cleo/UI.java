package cleo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides a user interface for interacting with the task management system.
 * This may include command-line or graphical interaction methods.
 */
public class UI {

    /**
     * Scanner to read user input (assuming command-line functionality).
     */
    private final Scanner scanner;

    /**
     * Constructor to initialize the UI.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The command string entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     *
     * @return The welcome message string
     */
    public String showWelcomeMessage() {
        return "     Hello! I'm Cleo\n"
                + "     What can I do for you?\n";
    }

    /**
     * Displays a goodbye message to the user.
     *
     * @return The goodbye message string
     */
    public String showGoodbyeMessage() {
        return "     Bye. Hope to see you again soon!\n";
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed
     * @return The formatted error message
     */
    public String showErrorMessage(String errorMessage) {
        return errorMessage + "\n";
    }

    /**
     * Displays a message confirming a task was added.
     *
     * @param task The task that was added
     * @param taskCount The updated total number of tasks
     * @return The confirmation message string
     */
    public String showTaskAdded(Task task, int taskCount) {
        return "     Got it. I've added this task:\n"
                + "       " + task + "\n"
                + "     Now you have " + taskCount + " tasks in the list.";
    }

    /**
     * Displays a formatted list of tasks.
     *
     * @param tasks An ArrayList of Task objects to be displayed
     * @return The formatted task list string
     */
    public String showTaskList(ArrayList<Task> tasks) {
        StringBuilder message = new StringBuilder("     Here are the tasks in your list:\n");
        if (tasks.isEmpty()) {
            message.append("     No tasks added yet.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                message.append("     ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }
        }
        return message.toString().trim(); // .trim() to remove the last newline character
    }

    /**
     * Displays a formatted list of tasks scheduled for a particular date.
     *
     * @param tasks An ArrayList of Task objects that are on the specified date
     * @param date The LocalDate object representing the date for which tasks are listed
     * @return The formatted task list string for the given date
     */
    public String showTasksOnDate(ArrayList<Task> tasks, LocalDate date) {
        StringBuilder message = new StringBuilder("Tasks on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":\n");
        if (tasks.isEmpty()) {
            message.append("No tasks found on this date.");
        } else {
            for (Task task : tasks) {
                message.append(task).append("\n");
            }
        }
        return message.toString().trim(); // .trim() to remove the last newline character
    }

    /**
     * Displays a message indicating the maximum task limit has been reached.
     *
     * @return The message string indicating maximum tasks reached
     */
    public String showMaximumTasksReached() {
        return "     Maximum tasks reached. Cannot add more tasks.";
    }

    /**
     * Displays a message that a task has been removed.
     *
     * @param task The Task object that was removed
     * @param taskCount The updated total number of tasks
     * @return The confirmation message string
     */
    public String showTaskRemoved(Task task, int taskCount) {
        return "     Noted. I've removed this task:\n"
                + "       " + task + "\n"
                + "     Now you have " + taskCount + " tasks in the list.";
    }

    /**
     * Displays a message confirming a task is marked as done.
     *
     * @param task The Task object marked as done
     * @return The confirmation message string
     */
    public String showTaskMarkedAsDone(Task task) {
        return "     Nice! I've marked this task as done:\n"
                + "       " + task;
    }

    /**
     * Displays a message confirming a task is marked as not done.
     *
     * @param task The Task object marked as not done
     * @return The confirmation message string
     */
    public String showTaskMarkedAsNotDone(Task task) {
        return "     OK, I've marked this task as not done yet:\n"
                + "       " + task;
    }

}
