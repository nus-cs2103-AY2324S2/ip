import java.util.ArrayList;

public class TextUi {
    private static final String DIVIDER_DOUBLE = "============================================================\r\n";
    private static final String DIVIDER_SINGLE = "____________________________________________________________\r\n";
    private static final String MICKEY = "    (\\_/)\n" + "   ( •,•)\n" + "   (\")_(\")\n";
    private static final String GREETING = "    Hello! I'm Mickey\n" + "    What can I do for you?\n";
    private static final String BYE = " Byeeee. See you soon!\n";
    private static final String ROAR = "    RAWR!!!\n";

    public static String addComment (Task task, int n) {
        if (n == 1) {
            return DIVIDER_DOUBLE +
                    " Got it, I have added this task:\n" + "    "+ task + "\n" + " Now you have " + n + " task in the list.\n" +
                    DIVIDER_DOUBLE;
        }
        return DIVIDER_DOUBLE +
                " Got it, I have added this task:\n" + "    "+ task + "\n" + " Now you have " + n + " tasks in the list.\n" +
                DIVIDER_DOUBLE;
    }

    public static void listTasks(ArrayList<Task> taskList, int count) {
        String taskMessage = " Here are the tasks in your list:\n";
        String message = DIVIDER_DOUBLE + taskMessage;

        System.out.print(message);
        for (int i = 0; i < taskList.size(); i++ ) {
            int number = i + 1;
            String result = "   " + number + ". " + taskList.get(i);
            System.out.println(result);
        }
        System.out.println(DIVIDER_DOUBLE);
    }

    public static String markMessage(Task task) {
        String message;
        if (task.isDone()) {
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

    public static String introMessage() {
        return DIVIDER_DOUBLE + ROAR + MICKEY + DIVIDER_SINGLE + GREETING + DIVIDER_DOUBLE;
    }

    public static String outroMessage() {
        return DIVIDER_DOUBLE + BYE + DIVIDER_DOUBLE;
    }

    public static String deleteMessage(Task task, int count) {
        String message = DIVIDER_DOUBLE +
                " Noted. I have removed this task:\n" + "   " + task + "\n" + "Now you have " +
                count + " tasks in the list.\n" +
                DIVIDER_DOUBLE;
        return message;
    }

    public static String replacer(String input) {
        return input.replaceAll("/(\\w+)", "$1:");
    }

    public static String extractTaskName(String input) {
        int index = input.indexOf("/");
        int secondWord = input.indexOf(" ");
        return  input.substring(secondWord + 1, index);
    }

}
