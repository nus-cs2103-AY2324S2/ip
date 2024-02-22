package anxi.command;

import java.util.ArrayList;

/**
 * Handles interaction with user.
 */
public class Ui {
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
        String logo = "                           _      _\n"
                + "      _        _    _    \\ \\  / /    _\n"
                + "    /   \\     |  \\|  |     \\ \\/ /    | |\n"
                + "  /  ()  \\    |      |    / /\\ \\     | |\n"
                + "/__/ \\__\\  |_|\\__|  /_/  \\_\\   |_|\n";
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
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Returns task list string to be printed to GUI.
     * @return taskList     Consolidated task list.
     */
    public String printTaskList(String tasks) {
        if (tasks.isEmpty()) {
            return "Woo hoo no tasks in list!";
        }

        StringBuilder stringBuilder = new StringBuilder("\nTasks in your list:\n\n");
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
        StringBuilder stringBuilder = new StringBuilder("\nMarked as done. Good Job!\n ");
        stringBuilder.append(taskString);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    /**
     * Returns task unmarked message to be printed to GUI.
     *
     * @param taskString        String representation of task.
     * @return unmarkedTask     Information of successfully unmarked task.
     */
    public String printUnmarkTask(String taskString) {
        StringBuilder stringBuilder = new StringBuilder("\nSuch a disappointment. Unmarked the task.\n ");
        stringBuilder.append(taskString);
        stringBuilder.append("\n");
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
        StringBuilder stringBuilder = new StringBuilder("\nBoo hoo :( added new task.\n ");
        stringBuilder.append(taskString);
        stringBuilder.append("\n");
        stringBuilder.append(numOfTasks);
        stringBuilder.append(" task(s) in the list.\n");
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
        StringBuilder stringBuilder = new StringBuilder("\nFarewell task! Task removed from list\n ");
        stringBuilder.append(taskString);
        stringBuilder.append("\n");
        stringBuilder.append(numOfTasks);
        stringBuilder.append(" task(s) in the list.\n");
        return stringBuilder.toString();
    }

    /**
     * Returns find task message to be printed to GUI.
     * @return findTask     All tasks that contain/match input string.
     */
    public String printFindTask(String tasks) {
        StringBuilder stringBuilder = new StringBuilder("\nMatching tasks in list:\n");
        stringBuilder.append(tasks);
        return stringBuilder.toString();
    }

    /**
     * Returns unknown command message to be printed to GUI.
     *
     * @param command   Input command string.
     * @return unknownCommandString     Indicates that command entered is not a known command.
     */
    public String printUnknownCommandError() {
        return "What's that? Invalid request.";
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
        return "\nEvents on " + date + ":\n" + events
                + "\nDeadlines on " + date + ":\n" + deadlines;
    }
}
