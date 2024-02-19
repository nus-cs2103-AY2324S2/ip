package util;

import tasks.Task;
import util.TaskList;

public class TextUi {
    private static final String DIVIDER_DOUBLE = "============================================================\r\n";
    private static final String DIVIDER_SINGLE = "____________________________________________________________\r\n";
    private static final String MICKEY = "    (\\_/)\n" + "   ( •,•)\n" + "   (\")_(\")\n";
    private static final String GREETING = "    Hello! I'm Mickey\n" + "    What can I do for you?\n";
    private static final String BYE = " Byeeee. See you soon!\n";
    private static final String ROAR = "    RAWR!!!\n";
    private static final String ERROR_MESSAGE = "OOOPS! Something went WRONGG!";

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

    public String showTaskList(TaskList taskList) {
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
        return DIVIDER_DOUBLE + ROAR + MICKEY + DIVIDER_SINGLE + GREETING + DIVIDER_DOUBLE;
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
