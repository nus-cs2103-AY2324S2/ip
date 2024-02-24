package luke;

import luke.task.Task;

/**
 * In charge of printing statements, to confirm to the user that the action is completed.
 */
public class Ui {
    public static final String LINE = "________________________________________________________\n";

    /**
     * Returns the welcome message.
     * @return The welcome message.
     */
    public String displayWelcome() {
        return LINE
                + "Hello! I'm Luke!\n"
                + "What can I do for you?\n"
                + LINE;
    }

    /**
     * Returns the goodbye message.
     * @return The goodbye message.
     */
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

    /**
     * Returns the task list, concatenated with the ui message.
     * @param taskList The task list to be displayed.
     * @return The task list, concatenated with the ui message.
     */
    public String displayTasks(List taskList) {
        assert taskList != null;
        return LINE
                + "Here are the tasks in your list:\n"
                + taskList.toString() + "\n"
                + LINE;
    }

    /**
     * Returns the marked task, concatenated with the ui message.
     * @param task The marked task.
     * @return The marked task, concatenated with the ui message.
     */
    public String displayTaskMarked(Task task) {
        assert task != null;
        return LINE
                + "Nice! I've marked this task as done:\n"
                + task.toString() + "\n"
                + LINE;
    }

    /**
     * Returns the unmarked task, concatenated with the ui message.
     * @param task The unmarked task.
     * @return The unmarked task, concatenated with the ui message.
     */
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

    /**
     * Returns the matching tasks, concatenated with the ui message.
     * @param results The list of matching tasks.
     * @return The matching tasks, concatenated with the ui message.
     */
    public String displayResults(List results) {
        assert results != null;
        return LINE
                + "Here are the matching tasks in your list:\n"
                + results.toString() + "\n"
                + LINE;
    }

    /**
     * Returns the help message.
     * @return The help message.
     */
    public String displayHelp() {
        return LINE
                + "How to use:\n"
                + "Luke is a simple task list manager. You (the person on the right) can interact with Luke (the\n"
                + "person on the left) through commands to manage your tasks.\n"
                + "If this is your first time using this app, sample data has already been loaded for you. Feel free\n"
                + "to experiment with them and delete them if not in use.\n"
                + "Your tasks are automatically saved to the save file. Warning: do not edit the save file directly,\n"
                + "or the save file may be corrupted! In this case, you may possibly lose all your saved tasks!\n"
                + "\n"
                + "Here is the list of commands you may use:\n"
                + "help, todo, deadline, event, list, mark, unmark, delete, find, bye\n"
                + "Here is how to use each of the commands:"
                + "todo [task description] - adds a todo of the specified description, without a start or end date,\n"
                + "to the task list.\n"
                + "deadline [task description] /by [end date] - adds a deadline of the specified description, with\n"
                + "the specified end date, to the task list.\n"
                + "event [task description] /from [start date] /to [end date] - adds an event of the specified\n"
                + "description, with the specified start and end date, to the task list.\n"
                + "list - lists all the tasks in the task list.\n"
                + "mark INDEX - marks the task with that particular index as completed. For example, mark 2 would\n"
                + "task 2, i.e. the 2nd task in the list, as completed. Completed tasks are denoted with 'X'.\n"
                + "unmark INDEX - unmarks the task with that particular index as not completed. For example, unmark 2\n"
                + "would unmark task 2, i.e. the 2nd task in the list, as not completed.\n"
                + "delete INDEX - deletes the task with that particular index. For example, delete 2 would delete\n"
                + "task 2, i.e. the 2nd task in the list.\n"
                + "Note: deleting a task would shift any tasks below it upwards. For example, task 3 would become\n"
                + "task 2 (the second task in the list) after deleting task 1 (the first task in the list). Thus\n"
                + "deleting tasks may cause the indexes of other tasks to change - please double check the indexes\n"
                + "of the tasks before editing them.\n"
                + "find [keyword(s)] - finds all tasks whose description contains keyword(s) (as a substring).\n"
                + "bye - exits the program\n"
                + LINE;
    }
}
