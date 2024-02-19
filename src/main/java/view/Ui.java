package view;
import model.Task;
import model.TaskList;

/**
 * Stores and returns text replies.
 */
public class Ui {
    /**
     * Returns a generic error message with the detail message string of the exception into the UI.
     * 
     * <p>Informs the user that an unexpected error was encountered.
     * 
     * @param e Exception with detail message string.
     */
    public static String showError(Exception e) {
        return String.format("Error Detected: %s", e.getMessage());
    }

    /**
     * Returns an error message with the user's input into the UI.
     * 
     * <p>Informs the user that the command entered is not a valid command.
     * 
     * @param cmd Invalid user input.
     */
    public static String showInvalidCommand(String cmd) {
        return String.format("I don't understand what you mean by \"%s\"\nPlease request something like:\n"
                + "  bye, list, mark, delete, todo, deadline, event.", cmd);
    }

    public static String showEmptyCommand() {
        return "      \u2571\u007c\u3001\n"
                + "    \u0028\u02da\u02ce\u0020\u3002\u0037\n"
                + "     \u007c\u3001\u02dc\u3035\n"
                + "    \u3058\u3057\u02cd\u002c\u0029\u30ce";
    }

    /**
     * Returns a greeting with the bot's name.
     * 
     * @param name Name of the bot.
     */
    public static String showGreet(String name) {
        return String.format("Hello! I'm %s.\nWhat can I do for you?", name);
    }

    /**
     * Returns a goodbye message.
     */
    public static String showBye() {
        return String.format("Bye. Hope to see you again soon!");
    }

    /**
     * Returns the tasks in the format:
     * <blockquote><pre>
     *  1.
     *  2.
     *  3.
     * </pre></blockquote><p>
     * if there are tasks in the supplied {@code TaskList}. Else, prints a no tasks message into the UI.
     * 
     * @param tasks {@code TaskList} object to print.
     */
    public static String showTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            return String.format("There are currently no tasks in your list.");
        } else {
            return String.format("Here are the tasks in your list:\n%s", tasks);
        }
    }

    /**
     * Returns an error message into the UI.
     * 
     * <p>Informs the user that the task selected does not exist.
     * 
     * @param nTasks Number of tasks in the {@code TaskList}.
     */
    public static String showIndexOutOfBoundsError(int nTasks) {
        return String.format("Task selected does not exist.\nTask number must be between 1 to %d.", nTasks);
    }

    /**
     * Returns an error message into the UI.
     * 
     * <p>Informs the user that the task number entered contains illegal characters that could not be parsed.
     */
    public static String showIndexParseError() {
        return String.format("Please enter a valid task number!\nOnly numerical letters [0-9] accepted.");
    }

    public static String showMarkDone(Task t) {
        return String.format("Nice! I've marked this task as done:\n  %s", t);
    }

    public static String showUnmarkDone(Task t) {
        return String.format("OK, I've marked this task as not done yet:\n  %s", t);
    }

    public static String showDeleteDone(Task t, int nTasks) {
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.",
                t, nTasks);
    }

    public static String showMissingTaskNameError() {
        return String.format("Please enter a NAME for your task!");
    }

    public static String showAddTaskDone(Task t, int nTasks) {
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                t, nTasks);
    }

    /**
     * Returns an error message into the UI.
     * 
     * <p> Informs the user that the input date and time in a field is in the wrong format.
     * 
     * @param format Date and time format the user should follow.
     * @param taskType Type of task - Deadline / Event / etc.
     * @param dateType The field the error was encountered in - By / To / From.
     */
    public static String showDateTimeParseError(String format, String taskType, String dateType) {
        return String.format("Please enter a valid DATE and TIME (%s)\nfor your %s task's [%s] date.",
                format, taskType, dateType);
    }

    public static String showAllMatchingTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            return String.format("No matching tasks found.");
        } else {
            return String.format("Here are the matching tasks in your list:\n%s", tasks);
        }
    }

    public static String showMissingFindArgError() {
        return String.format("Please enter a TEXT to find the Task!");
    }
}
