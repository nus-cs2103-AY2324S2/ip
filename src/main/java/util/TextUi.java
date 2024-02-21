package util;

import exceptions.DukeException;
import tasks.Task;
import util.TaskList;

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

    public String showErrorMessage(String message) {
        return ERROR_MESSAGE + message;
    }

    public String showTaskAdded(Task task, int n) {
        if (n == 1) {
            return DIVIDER_DOUBLE +
                    " Got it, I have added this task:\n" + "    "+ task + "\n" + " Now you have " + n + " task in the list.\n" +
                    DIVIDER_DOUBLE;
        }
        return DIVIDER_DOUBLE +
                " Got it, I have added this task:\n" + "    "+ task + "\n" + " Now you have " + n + " tasks in the list.\n" +
                DIVIDER_DOUBLE;
    }

    public String showTaskList(TaskList taskList) throws DukeException {
        String taskMessage = " Here are the tasks in your list:\n";
        String message = DIVIDER_DOUBLE + taskMessage;
        String result = "";
        for (int i = 0; i < taskList.getTaskListLength(); i++ ) {
            int number = i + 1;
            result += "   " + number + ". " + taskList.getTask(i) + "\n";
        }
        return message + result + DIVIDER_DOUBLE;
    }

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

    public String showIntroMessage() {
        return DIVIDER_DOUBLE + CHILLCHIEF + GREETING + HELP_MESSAGE + DIVIDER_DOUBLE;
    }

    public String showCommands() {
        return COMMANDS;
    }

    public String showOutroMessage() {
        return DIVIDER_DOUBLE + BYE + DIVIDER_DOUBLE;
    }

    public String showDeletedTask(Task task, int count) {
        String message = DIVIDER_DOUBLE +
                " Noted. I have removed this task:\n" + "   " + task + "\n" + "Now you have " +
                count + " tasks in the list.\n" +
                DIVIDER_DOUBLE;
        return message;
    }

}
