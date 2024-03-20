package duke.ui;

import java.util.Scanner;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Class represents UI functionalities.
 */
public class UI {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays greeting sentence.
     */
    public String onEnter() {
        return "Hello! I'm Plaudern\nWhat can I do for you?\n";
    }

    /**
     * Displays task list.
     */
    public String showList(TaskList taskList) {
        return "Here are the tasks in your list:\n" + taskList.listTask();
    }

    /**
     * Displays matching task list.
     *
     * @param taskList List to be displayed.
     */
    public String showMatchedList(TaskList taskList) {
        return "Here are the matching tasks in your list:\n" + taskList.listTask();
    }

    /**
     * Displays success message after add task to the list.
     *
     * @param task  Task added.
     * @param count Number of tasks in the list after the addition.
     */
    public String onAddSuccess(Task task, Integer count) {
        return "Got it. I've added this task:\n" + task + "\n" + "Now you have " + count + " tasks in the list.";
    }

    /**
     * Displays success message after mark task.
     *
     * @param task Task marked
     */
    public String onMarkDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Displays success message after unmark task.
     *
     * @param task Task unmarked.
     */
    public String onUnmarkDone(Task task) {
        return "Nice! I've unmarked this task as undone:\n" + task;
    }

    /**
     * Displays success message after delete task.
     *
     * @param task     Task deleted.
     * @param taskList Task list after deletion.
     */
    public String onDelete(Task task, TaskList taskList) {
        return "Noted. I've removed this task:\n" + task + "\n" + "Now you have "
            + taskList.getNumOfTasks() + " tasks in the list.";
    }

    /**
     * Displays general error message.
     *
     * @param msg Error message.
     */
    public String showErrorMsg(String msg) {
        return msg;
    }

    /**
     * Displays loading error.
     */
    public String showLoadingError() {
        return "Error occur when initiating the resources.";
    }

    /**
     * Reads one line of user input.
     *
     * @return User input
     */
    public String getUserInput() {
        return this.scanner.nextLine();
    }

    /**
     * Displays exit message.
     */
    public String onExit() {
        return "Bye. Hope to see you again soon!" + "(The window will be closed automatically in 3 seconds)";
    }

    /**
     * Displays number of tasks in the list.
     *
     * @param count Number of tasks.
     */
    public String showTaskListCount(Integer count) {
        return "Now you have " + count + " tasks in the list.";
    }
}
