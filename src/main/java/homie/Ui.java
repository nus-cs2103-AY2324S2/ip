package homie;

/**
 * Ui Class to handle any output to user
 */
public class Ui {
    public static final String DIVIDER = "________________________________________";
    public Ui() {

    }

    /**
     * Shows welcome message when user first open application.
     *
     * @return String message for welcome.
     */
    public String showWelcomeMessage() {
        return DIVIDER + "\nHello! I'm Homie" + "What can I do for you?\n" + DIVIDER;
    }

    /**
     * Shows goodbye message when user exits the application.
     *
     * @return String message for goodbye.
     */
    public String showGoodbyeMessage() {
        return DIVIDER + "\nBye Homie. Hope to see you again soon!\n" + DIVIDER;
    }

    /**
     * Shows loading error message when there is an error loading tasks from storage.
     *
     * @return String message for error loading tasks.
     */
    public String showLoadingError() {
        return DIVIDER + "\nHomie, theres an error loading your tasks!\n" + DIVIDER;
    }

    /**
     * Shows all the tasks in the task lists.
     *
     * @return String message to show all tasks in the list.
     */
    public String showListMessage(TaskList tasks) {
        return DIVIDER + "\nHere are the tasks in your list:\n" + tasks.getTasks() + DIVIDER;
    }

    /**
     * Shows delete message after deleting a task
     *
     * @param task The task object to be deleted
     * @param tasks The task lists that stores all the task objects
     * @return String message to acknowledge the task has been deleted
     */
    public String showDeleteMessage(Task task, TaskList tasks) {
        return DIVIDER + "\nNoted. I've removed this task:\n" + "\t" + task.toString()
                + "\nNow you have " + tasks.getSize() + " tasks in the list.\n" + DIVIDER;
    }

    /**
     * Shows message after adding a to-do task
     *
     * @param task The task object to be added
     * @param tasks The task lists that stores all the task objects
     * @return String message to acknowledge that the to-do task has been added
     */
    public String showToDoMessage(Task task, TaskList tasks) {
        return DIVIDER + "\nGot it. I've added this task:\n" + "\t" + task.toString()
                + "\nNow you have " + tasks.getSize() + " tasks in the list.\n" + DIVIDER;
    }

    /**
     * Shows message after adding a deadline task
     *
     * @param task The deadline task to be added
     * @param tasks The task lists that stores all the task objects
     * @return String message to acknowledge that the deadline task has been added
     */
    public String showDeadlineMessage(Task task, TaskList tasks) {
        return DIVIDER + "\nGot it. I've added this task:\n" + "\t" + task.toString()
                + "\nNow you have " + tasks.getSize() + " tasks in the list.\n" + DIVIDER;
    }

    /**
     * Shows message after added an event task.
     *
     * @param task The event task to be added.
     * @param tasks The task lists that stores all the tasks object.
     * @return String message to acknowledge that the event task has been added.
     */
    public String showEventMessage(Task task, TaskList tasks) {
        return DIVIDER + "\nGot it. I've added this task:\n" + "\t" + task.toString()
                + "\nNow you have " + tasks.getSize() + " tasks in the list.\n" + DIVIDER;
    }

    /**
     * Shows message after marking a task.
     *
     * @param task The task to be marked.
     * @return String message to acknowledge task has been marked.
     */
    public String showMarkMessage(Task task) {
        return DIVIDER + "\nNice! I've marked this task as done:\n" + "\t" + task.toString() + "\n" + DIVIDER;
    }

    /**
     * Shows message when un marking a task.
     *
     * @param task The task to be unmarked.
     * @return String message to acknowledge task has been unmarked.
     */
    public String showUnmarkMessage(Task task) {
        return DIVIDER + "\nOk, I've marked this task as not done yet:\n" + "\t" + task.toString() + "\n" + DIVIDER;
    }

    /**
     * Shows message after user entered a wrong command.
     *
     * @return String message that user has entered a wrong command.
     */
    public String showWrongCommand() {
        return DIVIDER + "\nWrong Command!\n" + DIVIDER;
    }

    /**
     * Shows tasks with the matching keyword.
     *
     * @return String message of all tasks with matching keyword.
     */
    public String showFindMessage(TaskList tasks, String keyword) {
        String matchingTasks = tasks.findTask(keyword);
        return DIVIDER + "\nHere are the matching tasks in your list:\n" + matchingTasks + DIVIDER;
    }

    /**
     * Show empty to-do description task message
     *
     * @return String message of to-do description cannot be empty
     */
    public String showEmptyTodoDescriptionMessage() {
        return DIVIDER + "\nPlease la bro, Todo description cannot be empty...\nWhat you want to add liddat?\n"
                + DIVIDER;
    }

    /**
     * Prints message to CLI
     *
     * @param message String message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}
