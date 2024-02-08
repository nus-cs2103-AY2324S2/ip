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
     * @return welcomeMessage   String
     */
    public String printWelcomeMessage() {
        StringBuilder stringBuilder = new StringBuilder(logo);
        stringBuilder.append("\nHello! I'm ");
        stringBuilder.append(name);
        stringBuilder.append("\r\nWhat can I do for you? \r\n");
        stringBuilder.append(line);
        return stringBuilder.toString();
    }

    /**
     * Print exit message.
     */
    public String printExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Print error loading tasks from file.
     */
    public String showLoadingError() {
        return "File corrupted, unable to load saved tasks \nResetting list.............................";
    }

    /**
     * Print a line.
     */
    public String printLine() {
        return line;
    }

    /**
     * Print task list message.
     */
    public String printTaskList(String tasks) {
        StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list:\n");
        stringBuilder.append(tasks);
        return stringBuilder.toString();
    }

    /**
     * Print task marked message.
     *
     * @param taskString    String representation of task.
     */
    public String printMarkTask(String taskString) {
        StringBuilder stringBuilder = new StringBuilder("Nice! I've marked this task as done:\n ");
        stringBuilder.append(taskString);
        return stringBuilder.toString();
    }

    /**
     * Print task unmarked message.
     *
     * @param taskString    String representation of task.
     */
    public String printUnmarkTask(String taskString) {
        StringBuilder stringBuilder = new StringBuilder("OK, I've marked this task as not done yet:\n ");
        stringBuilder.append(taskString);
        return stringBuilder.toString();
    }

    /**
     * Print add task message.
     *
     * @param taskString    String representation of task.
     * @param numOfTasks    Total number of tasks in list.
     */
    public String printAddTask(String taskString, int numOfTasks) {
        StringBuilder stringBuilder = new StringBuilder("Got it. I've added this task:\r\n ");
        stringBuilder.append(taskString);
        stringBuilder.append("\nNow you have ");
        stringBuilder.append(numOfTasks);
        stringBuilder.append(" tasks in the list.");
        return stringBuilder.toString();
    }

    /**
     * Print delete task message.
     *
     * @param taskString    String representation of task.
     * @param numOfTasks    Total number of tasks in list.
     */
    public String printDeleteTask(String taskString, int numOfTasks) {
        StringBuilder stringBuilder = new StringBuilder("Noted. I've removed this task:\r\n ");
        stringBuilder.append(taskString);
        stringBuilder.append("\nNow you have ");
        stringBuilder.append(numOfTasks);
        stringBuilder.append(" tasks in the list.");
        return stringBuilder.toString();
    }

    /**
     * Print find task message.
     */
    public String printFindTask(String tasks) {
        StringBuilder stringBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
        stringBuilder.append(tasks);
        return stringBuilder.toString();
    }

    /**
     * Print unknown command message.
     *
     * @param command   Input command string.
     */
    public String printUnknownCommandError(String command) {
        StringBuilder stringBuilder = new StringBuilder("Are you as clueless about \"");
        stringBuilder.append(command);
        stringBuilder.append("\" as I am?");
        return stringBuilder.toString();
    }

    /**
     * Print error message.
     *
     * @param errorMessage      Error message string.
     */
    public String printErrorMessage(String errorMessage) {
        return errorMessage;
    }
}
