package rochin;

import java.util.List;

/**
 * A class representing the user interactions.
 */
public class Ui {

    /**
     * Display the welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Rochin.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Display the goodbye message.
     */
    public void showGoodbyeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Display the command prompt.
     */
    public void showCommandPrompt() {
        System.out.print("Enter a command: ");
    }

    /**
     * Display an error message for loading tasks.
     */
    public void showLoadingError() {
        System.out.println("Failed to load tasks. Creating a new task list.");
    }

    /**
     * Display an error message for saving tasks.
     */
    public void showSavingError() {
        System.out.println("Failed to save tasks.");
    }

    /**
     * Display a generic error message with the provided errorMessage.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Display an unknown command error message.
     */
    public void showUnknownCommandError() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Display an invalid command error message.
     */
    public static void showInvalidCommandError() {
        System.out.println("Invalid command. Please enter a valid command.");
    }

    /**
     * Display the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(List<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Display a message confirming the addition of a task.
     *
     * @param tasks The updated list of tasks.
     */
    public void showTaskAddedMessage(List<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Display a message confirming the deletion of a task.
     *
     * @param tasks The updated list of tasks.
     */
    public void showTaskDeletedMessage(List<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Display a message confirming the marking of a task as done.
     *
     * @param tasks The updated list of tasks.
     */
    public void showTaskMarkedAsDoneMessage(List<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("____________________________________________________________");
    }

    /**
     * Display a message confirming the marking of a task as not done.
     *
     * @param tasks The updated list of tasks.
     */
    public void showTaskUnmarkedAsDoneMessage(List<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("____________________________________________________________");
    }

    public String getTextOutput() {
        return "";
    }
}
