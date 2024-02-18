package kitchensink;

import kitchensink.task.Task;

/**
 * In charge of printing statements, to confirm to the user that the action is completed.
 */
public class Ui {
    public static final String LINE = "________________________________________________________\n";

    public String displayWelcome() {
        return LINE
                + "Hello! I'm KitchenSink!\n"
                + "What can I do for you?\n"
                + LINE;
    }

    public String displayGoodBye() {
        return LINE
                + "Bye. Hope to see you again soon!\n"
                + LINE;
    }

    /**
     * Confirms to the user that the specified task is added.
     * Displays the number of tasks remaining and distinguishes between singular and plural form.
     * @param task The task that is being added.
     * @param taskListSize Number of tasks in taskList.
     * @return String to display in the ui.
     */
    public String displayTaskAdded(Task task, int taskListSize) {
        assert task != null;
        String taskOrTasks = taskListSize == 1 ? " task " : " tasks ";
        return LINE
                + "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + taskListSize + taskOrTasks + "in the list.\n"
                + LINE;
    }

    public String displayTasks(List taskList) {
        assert taskList != null;
        return LINE
                + "Here are the tasks in your list:\n"
                + taskList.toString()
                + "\n" + LINE;
    }

    public String displayTaskMarked(Task task) {
        assert task != null;
        return LINE
                + "Nice! I've marked this task as done:\n"
                + task.toString() + "\n"
                + LINE;
    }

    public String displayTaskUnmarked(Task task) {
        assert task != null;
        return LINE
                + "OK, I've marked this task as not done yet:\n"
                + task.toString() + "\n"
                + LINE;
    }

    /**
     * Confirms to the user that the specified task is deleted.
     * Displays the number of tasks remaining and distinguishes between singular and plural form.
     * @param task The task that is being deleted.
     * @param taskListSize Number of tasks in taskList.
     * @return String to display in the ui.
     */
    public String displayTaskDeleted(Task task, int taskListSize) {
        assert task != null;
        String taskOrTasks = taskListSize == 1 ? " task " : " tasks ";
        return LINE
                + "Noted. I've removed this task:\n"
                + task.toString()
                + "\nNow you have " + taskListSize + taskOrTasks + "in the list.\n"
                + LINE;
    }

    public String displayResults(List results) {
        assert results != null;
        return LINE
                + "Here are the matching tasks in your list:\n"
                + results.toString() + "\n"
                + LINE;
    }
}
