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
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm MichelleBot! What can I do for you? (helpg for guide)");
        System.out.println("____________________________________________________________");
    }

    /**
     * Show message after adding a task to the list.
     *
     * @param addedTask task to be added to
     * @param size size of the list
     */
    public void showAddTaskMessage(Task addedTask, int size) {
        if (addedTask != null) {
            System.out.println("Roger that! I've added in this task:\n " + addedTask);
            System.out.println("Now you have " + size + " tasks in the list.");
        }
    }

    /**
     * Show message after deleting a task from the list.
     *
     * @param deletedTask task that was deleted
     * @param size size of the list
     */
    public void showDeletedTaskMessage(Task deletedTask, int size) {
        if (deletedTask != null) {
            System.out.println(
                "Roger that! I've removed this task:\n "
                + deletedTask
                + "\nNow you have "
                + size
                + " tasks in the list.");
        }
    }

    /**
     * Show message after task is marked.
     *
     * @param markedTask Task that is marked.
    */
    public void showMarkMessage(Task markedTask) {
        if (markedTask != null) {
            System.out.println("I've marked this task as done: \n" + markedTask);
        }
    }

    /**
     * Show message after task is unmarked.
     *
     * @param unmarkedTask Task that is marked.
     */
    public void showUnmarkMessage(Task unmarkedTask) {
        if (unmarkedTask != null) {
            System.out.println("I've marked this task as not done yet: \n" + unmarkedTask);
        }
    }

    /**
     * Show error message if an error occurs.
     *
     * @param errorMessage message to be printed out
     */
    public void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    /** Show help message explaining the commands in the program. */
    public void showHelpMessage() {
        System.out.println("Type in text to add in a task to your list\n"
            + "Other commands:\n"
            + "add [task] - adds a task to the task list\n"
            + "mark [input number] - mark a task as done\n"
            + "unmark [input number] - unmark a task as undone\n"
            + "todo [task] - add a TODO task to your list\n"
            + "deadline [task] /by [deadline] - add a DEADLINE to your list\n"
            + "event [task] /from [date] /to [date] - add an EVENT to your list\n"
            + "delete [input number] - delete a task from task list\n"
            + "list - list out the current tasks you have\n"
            + "bye - exit the program\n"
            + "(NOTE: deadline should be in dd-mm-yyyy hhmm format)");
    }

    /** Show end message when program is exited. */
    public void showEndMessage() {
        System.out.println("Bye. Hope to see you again soon! \\(^-^)/ ");
    }
}
