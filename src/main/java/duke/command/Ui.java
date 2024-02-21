package duke.command;

/**
 * Handles interaction with user.
 */
public class Ui {
    private final String Line = "------------------------------------------------------------";

    /**
     * Ui constructor.
     */
    public Ui() {
    }

    /**
     * Returns welcome message to be printed to GUI.
     * @return welcomeMessage   Welcome message to be printed.
     */
    public String printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "Anxi";
        StringBuilder stringBuilder = new StringBuilder(logo);
        stringBuilder.append("\nHello! I'm ");
        stringBuilder.append(name);
        stringBuilder.append("\r\nWhat can I do for you?");
        return stringBuilder.toString();
    }

    /**
     * Returns exit message to be printed to GUI.
     * @return exitMessage  Exit message to be printed.
     */
    public String printExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints error loading tasks from file.
     */
    public void showLoadingError() {
        System.out.println("File corrupted, unable to load saved tasks \nResetting list.............................");
    }

    /**
     * Prints a line.
     */
    public void printLine() {
        System.out.println(Line);
    }

    /**
     * Returns task list string to be printed to GUI.
     * @return taskList     Consolidated task list.
     */
    public String printTaskList(String tasks) {
        StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list:\n");
        stringBuilder.append(tasks);
        return stringBuilder.toString();
    }

    /**
     * Returns task marked message to be printed to GUI.
     *
     * @param taskString    String representation of task.
     * @return markedTask   Information of successfully marked task.
     */
    public String printMarkTask(String taskString) {
        StringBuilder stringBuilder = new StringBuilder("Nice! I've marked this task as done:\n ");
        stringBuilder.append(taskString);
        return stringBuilder.toString();
    }

    /**
     * Returns task unmarked message to be printed to GUI.
     *
     * @param taskString        String representation of task.
     * @return unmarkedTask     Information of successfully unmarked task.
     */
    public String printUnmarkTask(String taskString) {
        StringBuilder stringBuilder = new StringBuilder("OK, I've marked this task as not done yet:\n ");
        stringBuilder.append(taskString);
        return stringBuilder.toString();
    }

    /**
     * Returns add task message to be printed to GUI.
     *
     * @param taskString    String representation of task.
     * @param numOfTasks    Total number of tasks in list.
     * @return addedTask    The newly added task and number of tasks in task list.
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
     * Returns delete task message to be printed to GUI.
     *
     * @param taskString    String representation of task.
     * @param numOfTasks    Total number of tasks in list.
     * @return deletedTask  The deleted task and number of tasks left in task list.
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
     * Returns find task message to be printed to GUI.
     * @return findTask     All tasks that contain/match input string.
     */
    public String printFindTask(String tasks) {
        StringBuilder stringBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
        stringBuilder.append(tasks);
        return stringBuilder.toString();
    }

    /**
     * Returns unknown command message to be printed to GUI.
     *
     * @param command   Input command string.
     * @return unknownCommandString     Indicates that command entered is not a known command.
     */
    public String printUnknownCommandError(String command) {
        StringBuilder stringBuilder = new StringBuilder("Are you as clueless about \"");
        stringBuilder.append(command);
        stringBuilder.append("\" as I am?");
        return stringBuilder.toString();
    }

    /**
     * Returns error message to be printed to GUI.
     *
     * @param errorMessage      Error message string.
     * @return errorMessage     Error message string.
     */
    public String printErrorMessage(String errorMessage) {
        return errorMessage;
    }

    /**
     * Returns list of tasks on specific day to be printed to GUI.
     * @param date          Date tasks should be on.
     * @param events        Events on specific date.
     * @param deadlines     Deadlines on specific date.
     * @return tasks        String of all tasks that fall on specific date.
     */
    public String printTasksOnDay(String date, String events, String deadlines) {
        return "Events on " + date + ":\n" + events
                + "\nDeadlines on " + date + ":\n" + deadlines;
    }
}
