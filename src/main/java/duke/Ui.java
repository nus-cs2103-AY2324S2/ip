package duke;

/**
 * Deals with showing the correct messages to MainWindow controller.
 */
public class Ui {
    /**
     * Initializes Ui object.
     */
    public Ui() {
    }

    /**
     * Prints the welcome message.
     */
    private String botHeader() {
        return "DevGPT:\n\t";
    }

    /**
     * Provides bot name for the given messages.
     */
    private String botHeader(String message) {
        return "DevGPT " + message + ":\n\t";
    }

    /**
     * Prints the welcome message.
     */
    public String showWelcome() {
        return botHeader() + "Hello! I'm DevGPT\n\tWhat can I do for you?";
    }

    /**
     * Prints the task list.
     *
     * @param tasks the list of tasks to be printed.
     */
    public String showTaskList(TaskList tasks) throws DukeException {
        if (tasks.getSize() == 0) {
            throw new DukeException("No tasks found.");
        }
        String result = botHeader();
        result += "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            result += doubleTab() + (i + 1) + "." + tasks.getTask(i) + "\n";
        }
        return result;
    }
    /**
     * Shows the task marked as done.
     *
     * @param markTask the task to be marked as done.
     */
    public String showDone(Task markTask) {
        return botHeader() + "Nice! I've marked this task as done:"
            + doubleTab() + markTask;
    }
    /**
     * Shows the task marked as undone.
     *
     * @param unmarkTask the task to be marked as undone.
     */
    public String showUnmark(Task unmarkTask) {
        return botHeader() + "Got it! I've marked this task as not done yet:"
            + doubleTab() + unmarkTask;
    }

    /**
     * Shows that the task was deleted.
     *
     * @param deleteTask the task to be deleted.
     * @param size the number of tasks in the task list after deletion.
     */
    public String showDelete(Task deleteTask, int size) {
        assert size >= 0 : "Size should be greater than or equal to 0";
        return botHeader() + "Poof! I've removed this task:"
            + doubleTab() + deleteTask
            + "\n\t" + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Shows that the task was added.
     *
     * @param task the task to be added.
     * @param size the number of tasks in the task list after adding tasks.
     */
    public String showAddTask(Task task, int size) {
        assert size >= 0 : "Size should be greater than or equal to 0";
        return botHeader() + "Got it. I've added this task:"
            + doubleTab() + task + "\n\t"
            + "Now you have " + size + " tasks in the list.";
    }

    private String doubleTab() {
        return "\n\t\t";
    }

    /**
     * Shows the error message with error header.
     *
     * @param message the error message to be shown.
     */
    public String showError(String message) {
        return botHeader("Error") + message;
    }

    /**
     * Shows error message for invalid command.
     */
    public String commandNotUnderstood() {
        return showError("Your message is not understood. Please use following:\n\t"
            + "todo <description>\n\t"
            + "deadline <description> /by <dd-mm-yyyy> <HHmm> (time optional)\n\t"
            + "event <description> /from <dd-mm-yyyy> /to <dd-mm-yyyy>\n\t"
            + "list\n\t"
            + "mark <index>\n\t"
            + "unmark <index>\n\t"
            + "delete <index>\n\t"
            + "find <keyword>\n\t"
            + "bye");
    }

    /**
     * Shows bye message.
     */
    public String showBye() {
        return botHeader() + "Bye. Hope to see you again soon!";
    }

    public String showReminder(String message) {
        return botHeader("reminder") + message;
    }
}
