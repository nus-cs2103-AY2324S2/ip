package duke.command;

/**
 * Handles interaction with user.
 */
public class Ui {
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String name = "Anxi";
    private String line = "------------------------------------------------------------";

    /**
     * Ui constructor.
     */
    public Ui() {
    }

    /**
     * Print welcome message.
     */
    public void printWelcomeMessage() {
        System.out.println(line);
        System.out.println(logo);
        System.out.println("Hello! I'm " + name + "\r\nWhat can I do for you? \r\n" + line);
    }

    /**
     * Print exit message.
     */
    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!\r\n" + line);
    }

    /**
     * Print error loading tasks from file.
     */
    public void showLoadingError() {
        System.out.println("File corrupted, unable to load saved tasks");
        System.out.println("Resetting list.............................");
    }

    /**
     * Print a line.
     */
    public void printLine() {
        System.out.println(line);
    }

    /**
     * Print task list message.
     */
    public void printTaskList() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Print task marked message.
     *
     * @param taskString    String representation of task.
     */
    public void printMarkTask(String taskString) {
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + taskString);
    }

    /**
     * Print task unmarked message.
     *
     * @param taskString    String representation of task.
     */
    public void printUnmarkTask(String taskString) {
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + taskString);
    }

    /**
     * Print add task message.
     *
     * @param taskString    String representation of task.
     * @param numOfTasks    Total number of tasks in list.
     */
    public void printAddToDo(String taskString, int numOfTasks) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:\r\n " + taskString);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    /**
     * Print add event message.
     *
     * @param taskString    String representation of task.
     * @param numOfTasks    Total number of tasks in list.
     */
    public void printAddEvent(String taskString, int numOfTasks) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:\r\n " + taskString);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    /**
     * Print add deadline message.
     *
     * @param taskString    String representation of task.
     * @param numOfTasks    Total number of tasks in list.
     */
    public void printAddDeadline(String taskString, int numOfTasks) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:\r\n " + taskString);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    /**
     * Print delete task message.
     *
     * @param taskString    String representation of task.
     * @param numOfTasks    Total number of tasks in list.
     */
    public void printDeleteTask(String taskString, int numOfTasks) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + taskString);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    /**
     * Print find task message.
     */
    public void printFindTask() {
        System.out.println(line);
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Print unknown command message.
     *
     * @param command   Input command string.
     */
    public void printUnknownCommandError(String command) {
        System.out.println(line);
        System.out.println("Are you as clueless about \"" + command + "\" as I am?");
    }

    /**
     * Print error message.
     *
     * @param errorMessage      Error message string.
     */
    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}