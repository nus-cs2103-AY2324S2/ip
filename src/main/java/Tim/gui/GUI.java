package Tim.gui;

import Tim.storage.TaskList;
import Tim.task.Task;

public class GUI {


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
     * Returns string with available commands that can be used.
     *
     * @return message with all available commands
     */
    public static String showAvailCommands() {
        String commands = "Here are the list of commands:\n" +
                          "    1. list\n" +
                          "    2. deadline {task} /by {dd/mm/yyyy}\n" +
                          "    3. todo {task}\n" +
                          "    4. event {task} /from {dd/mm/yyyy} /to {dd/mm/yyyy}\n" +
                          "    5. checkdates /from {dd/mm/yyyy} /to {dd/mm/yyyy}\n" +
                          "    6. find\n" +
                          "    7. help\n" +
                          "    8. mark {task_num}\n" +
                          "    9. unmark {task_num}\n" +
                          "   10. delete {task_num}\n";
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

    public static String showMatchedTasks(TaskList taskList) {
        String msg = "Here are the matching tasks in your list:\n" + taskList.showALlTask();
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
    public static String showAddMsg(Task task, int taskNum){
        String output = "Ok, I have added this task:\n   " + task.toString() +
                String.format("\nNow you have %d tasks in the list\n", taskNum + 1);
        return output;
    }

    /**
     * Returns message that shows all the task that is within time period.
     *
     * @param task
     * @return String that shows all task in time period
     */
    public static String showTaskInScheduleMsg(TaskList taskList) {
        String output = "This are the tasks within the period you stated:\n" +
                taskList.showALlTask();
        return output;
    }
}
