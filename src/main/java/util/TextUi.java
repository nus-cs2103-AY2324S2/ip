package util;

import tasks.Task;

import exceptions.ChillChiefException;
/**
 * A TextUi class to represent the user interface for the ChillChief application.
 */
public class TextUi {

    private static final String DIVIDER_DOUBLE = "============================================================\r\n";
    private static final String DIVIDER_SINGLE = "____________________________________________________________\r\n";
    private static final String CHILLCHIEF = "";
    private static final String GREETING = "    G'day mate! ChillChief here\n" + "    What can I do for ya?\n";
    private static final String BYE = " Aye mate, hit me up if u need anything more yea\n";
    private static final String ERROR_MESSAGE = "OOOPS! Something went WRONGG!";
    private static final String HELP_MESSAGE = "If confused, type 'help' for a list of commands you can use!\n";
    private static final String COMMANDS = "Here is a list of commands you can use:\n"
            + "'list'\n"
            + "mark/unmark [task number]\n"
            + "delete [task number]\n"
            + "todo [task name]\n"
            + "deadline [task name] /by yyyy-mm-dd HHmm\n"
            + "event [task name] /from yyyy-mm-dd HHmm /to yyyy-mm-dd HHmm\n"
            + "'bye'\n";

    /**
     * Generates an error message for the user.
     *
     * @param message The specific error to display.
     * @return The error message with the specific error.
     */
    public String showErrorMessage(String message) {
        return ERROR_MESSAGE + message;
    }

    /**
     * Generates a message indicating successful addition of a task.
     *
     * @param task The task being added to the task list.
     * @param n    Number of tasks in the list.
     * @return The message indicating successful addition of a task.
     */
    public String showTaskAdded(Task task, int n) {
        if (n == 1) {
            return DIVIDER_DOUBLE +
                    " Got it, I have added this task:\n"
                    + "    "+ task + "\n"
                    + " Now you have "
                    + n
                    + " task in the list.\n"
                    + DIVIDER_DOUBLE;
        }
        return DIVIDER_DOUBLE +
                " Got it, I have added this task:\n"
                + "    "
                + task
                + "\n"
                + " Now you have "
                + n
                + " tasks in the list.\n"
                + DIVIDER_DOUBLE;
    }


    /**
     * Generates a message to show the current tasks in the task list.
     *
     * @param taskList The task list.
     * @return The message showing current tasks in the list.
     * @throws ChillChiefException If tasks could not be retrieved.
     */
    public String showTaskList(TaskList taskList) throws ChillChiefException {
        try {
            String taskMessage = " Here are the tasks in your list:\n";
            String message = DIVIDER_DOUBLE + taskMessage;
            String result = "";
            for (int i = 0; i < taskList.getTaskListLength(); i++ ) {
                int number = i + 1;
                result += "   " + number + ". " + taskList.getTask(i) + "\n";
            }
            return message + result + DIVIDER_DOUBLE;
        } catch (Exception e) {
            throw new ChillChiefException("Tasks could not be retrieved!");
        }
    }

    /**
     * Generates a message to show a task has been marked/unmarked.
     *
     * @param task the particular task being marked/unmarked.
     * @return A message to show a task has been marked/unmarked.
     */
    public String showMarkedOrUnmarkMessage(Task task) {
        String message;
        if (task.getDone()) {
            message = DIVIDER_SINGLE
                    + " Nice! I have marked this task as done:\n" + "   " + task + "\n"
                    + DIVIDER_SINGLE;
        } else {
            message = DIVIDER_SINGLE
                    + " OK, I have marked this task as not done yet:\n" + "   " + task + "\n"
                    + DIVIDER_SINGLE;
        }
        return message;
    }

    /**
     * Generates a welcome message for the user.
     *
     * @return A welcome message for the user.
     */
    public String showIntroMessage() {
        return DIVIDER_DOUBLE + CHILLCHIEF + GREETING + HELP_MESSAGE + DIVIDER_DOUBLE;
    }

    /**
     * Generates a message with a list of commands with their syntax's for the user.
     *
     * @return A message with a list of commands with their syntax's for the user.
     */
    public String showCommands() {
        return COMMANDS;
    }

    /**
     * Generates a farewell message for the user.
     *
     * @return A farewell message for the user.
     */
    public String showOutroMessage() {
        return DIVIDER_DOUBLE + BYE + DIVIDER_DOUBLE;
    }

    /**
     * Generates a message to show that a task has been removed from the task list.
     *
     * @param task The particular task that has been deleted.
     * @param count The number of tasks remaining in the list.
     * @return A message to show that a task has been removed from the task list.
     */
    public String showDeletedTask(Task task, int count) {
        return DIVIDER_DOUBLE +
                " Noted. I have removed this task:\n" + "   " + task + "\n" + "Now you have " +
                count + " tasks in the list.\n" +
                DIVIDER_DOUBLE;
    }
}
