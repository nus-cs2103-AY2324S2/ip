package Duke;

public class GUI {
    /**
     * Contains methods to display output for User Interface
     */

    /**
     * Generate start message.
     *
     * @return start message
     */
    public static String startMsg() {
        String msg = "Hello! I'm Tim \n" +
                "What can i do for you?\n";
        msg = msg + GUI.showAvailCommands();
        return msg;
    }

    /**
     * Generates exit message
     *
     * @return exit message
     */
    public static String exitMsg() {
        String msg = "Bye!!\n" +
                "Hope to see you again soon!";
        return msg;
    }

    /**
     * Returns string with available commands that can be used.
     */
    public static String showAvailCommands() {
        String commands = """
                Here are the list of commands:
                    1. list
                    2. bye
                    3. deadline {task} /by {date}
                    4. todo {task}
                    5. event {task} /from {date} /to {date}
                    6. check dates {date} {date}
                    7. find
                    8. help

                """;
        return commands;
    }

    /**
     * returns header message for when list command is used.
     *
     * @param contents
     */
    public static String listMsg(String contents) {
        String msg = "Here are the tasks in your list:\n" + contents;
        return msg;
    }

    public static String printMatchedTasks(String contents) {
        String msg = "Here are the matching tasks in your list:\n" + contents;
        return msg;
    }

    public static String markedMsg(Task task) {
        String output = "OK, I've marked this task as done:\n   " + task.toString();
        return output;
    }

    /**
     * Displays message for when a task is unmarked.
     * @param task
     */
    public static String unMarkedMsg(Task task) {
        String output = "OK, I've marked this task as not done:\n   " + task.toString();
        return output;
    }

    public static String deleteMsg(Task task, TaskList storage) {
        String output = "OK, I've removed this task:\n" +
                "   " + task.toString() +
                "\nNow you have " + storage.size() + " tasks in the list.";
        return output;
    }

    public static String printAddMsg(Task task, int taskNum){
        String output = "Ok, I have added this task:\n   " + task.toString() +
                String.format("\nNow you have %d tasks in the list\n", taskNum);
        return output;
    }

    public static String scheduledTaskMsg(String contents) {
        String output = "This are the tasks within the period you stated:\n" +
                contents;
        return output;
    }

    public static String duplicatesMsg() {
        String output = "The task had already been added to the list previously!";
        return output;
    }

}
