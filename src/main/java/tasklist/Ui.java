package tasklist;

import tasklist.tasks.Task;

/**
 * User interface - handles interaction with the user
 * Provides methods to manage the user interface of the application.
 *
 * Example usage:
 * Ui ui = new Ui();
 * ui.showWelcomeMessage();
 *
 */
public class Ui {
    /** keeps the user interface running. Set this variable to false to stop the program. */
    protected boolean isRunning = true;

    /**
    * Sets the value of the boolean variable.
    *
    * @param isRunning value to set the boolean variable to
    */
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /** show Welcome Message */
    public static String showWelcomeMessage() {
        return ("Hello! I'm MichelleBot! What can I do for you? (helpg for guide)");
    }

    /**
     * Show message after adding a task to the list.
     *
     * @param addedTask task to be added to
     * @param size size of the list
     */
    public String showAddTaskMessage(Task addedTask, int size) {
        if (addedTask != null) {
            return ("Roger that! I've added in this task:\n " + addedTask)
                + ("\nNow you have " + size + " tasks in the list.");
        }
        return null;
    }

    /**
     * Show message after deleting a task from the list.
     *
     * @param deletedTask task that was deleted
     * @param size size of the list
     */
    public String showDeletedTaskMessage(Task deletedTask, int size) {
        if (deletedTask != null) {
            return (
                "Roger that! I've removed this task:\n "
                + deletedTask
                + "\nNow you have "
                + size
                + " tasks in the list.");
        }
        return null;
    }

    /**
     * Show message after task is marked.
     *
     * @param markedTask Task that is marked.
    */
    public String showMarkMessage(Task markedTask) {
        if (markedTask != null) {
            return ("I've marked this task as done: \n" + markedTask);
        }
        return null;
    }

    /**
     * Show message after task is unmarked.
     *
     * @param unmarkedTask Task that is marked.
     */
    public String showUnmarkMessage(Task unmarkedTask) {
        if (unmarkedTask != null) {
            return ("I've marked this task as not done yet: \n" + unmarkedTask);
        }
        return null;
    }

    /**
     * Show error message if an error occurs.
     *
     * @param errorMessage message to be printed out
     */
    public String showErrorMessage(String errorMessage) {
        return (errorMessage);
    }

    /** Show end message when program is exited. */
    public String showEndMessage() {
        return ("Bye. Hope to see you again soon! \\(^-^)/ ");
    }
}
