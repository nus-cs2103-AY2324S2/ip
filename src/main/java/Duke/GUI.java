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
     *
     * @return message with all available commands
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
                    9. mark {task_num}
                   10. unmark {task_num}

                """;
        return commands;
    }

    /**
     * returns header message for when list command is used.
     *
     * @param contents
     * @return String with all the tasks in the list
     */
    public static String listMsg(String contents) {
        String msg = "Here are the tasks in your list:\n" + contents;
        return msg;
    }

    public static String printMatchedTasks(String contents) {
        String msg = "Here are the matching tasks in your list:\n" + contents;
        return msg;
    }

    /**
     * Returns message for when a task is marked.
     *
     * @param task
     * @return String that shows that the task is mark as done
     */
    public static String markedMsg(Task task) {
        String output = "OK, I've marked this task as done:\n   " + task.toString();
        return output;
    }

    /**
     * Returns message for when a task is unmarked.
     *
     * @param task
     * @return String that shows that the task is mark as not done
     */
    public static String unMarkedMsg(Task task) {
        String output = "OK, I've marked this task as not done:\n   " + task.toString();
        return output;
    }

    /**
     * Returns message for when a task is deleted.
     *
     * @param task
     * @return String that shows that the task is deleted
     */
    public static String deleteMsg(Task task, TaskList storage) {
        String output = "OK, I've removed this task:\n" +
                "   " + task.toString() +
                "\nNow you have " + storage.size() + " tasks in the list.";
        return output;
    }

    /**
     * Returns message for when a task is added.
     *
     * @param task
     * @return String that shows that the task is added
     */
    public static String printAddMsg(Task task, int taskNum){
        String output = "Ok, I have added this task:\n   " + task.toString() +
                String.format("\nNow you have %d tasks in the list\n", taskNum);
        return output;
    }

    /**
     * Returns message that shows all the task that is within time period.
     *
     * @param task
     * @return String that shows all task in time period
     */
    public static String scheduledTaskMsg(String contents) {
        String output = "This are the tasks within the period you stated:\n" +
                contents;
        return output;
    }

    public static String duplicatesMsg() {
        String output = "The task had already been added to the list previously!";
        return output;
    }

    /**
     *  Return specific string containing the error message to be used for certain commands.
     *
     * @param s
     * @return String for specified error
     */
    public static String errorMsg(String s) {
        String output = "";
        if (s.equals("delete")) {
            output = "What tasks do you want to delete?";
        } else if (s.equals("mark")) {
            output = "What tasks do you want to mark?";
        } else if (s.equals("unmark")) {
            output = "What tasks do you want to unmark?";
        } else if (s.equals("todo")) {
            output = "Where is the ToDo task?";
        } else if (s.equals("deadline")) {
            output = "Where is the Deadline task?";
        } else if (s.equals("event")) {
            output = "Where is the Event task?";
        } else if (s.equals("find")) {
            output = "What do you want to find?";
        }
        return output;
    }

}
