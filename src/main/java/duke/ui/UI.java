package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.Scanner;

/**
 * Class represents UI functionalities.
 */
public class UI {
	private Scanner scanner = new Scanner(System.in);

    /**
     * Displays greeting sentence.
     */
    public void onEnter() {
        System.out.println("Hello! I'm Plaudern\nWhat can I do for you?");
    }

    /**
     * Displays task list.
     */
    public void showList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        taskList.listTask();
    }

    /**
     * Displays matching task list.
     * @param taskList List to be displayed.
     */
    public void showMatchedList(TaskList taskList) {
        System.out.println("Here are the matching tasks in your list: ");
        taskList.listTask();
    }

    /**
     * Displays success message after add task to the list.
     * @param task Task added.
     * @param count Number of tasks in the list after the addition.
     */
    public void onAddSuccess(Task task, Integer count) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        this.showTaskListCount(count);
    }

    /**
     * Displays success message after mark task.
     * @param task Task marked
     */
    public void onMarkDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Displays success message after unmark task.
     * @param task Task unmarked.
     */
    public void onUnmarkDone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Displays success message after delete task.
     * @param task Task deleted.
     * @param taskList Task list after deletion.
     */
    public void onDelete(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        this.showTaskListCount(taskList.getNumOfTasks());
    }

    /**
     * Displays general error message.
     * @param msg Error message.
     */
    public void showErrorMsg(String msg) {
        System.out.println(msg);
    }

    /**
     * Displays loading error.
     */
    public void showLoadingError() {
        System.out.println("Error occur when initiating the resources.");
    }

    /**
     * Reads one line of user input.
     * @return User input
     */
    public String getUserInput() {
        return this.scanner.nextLine();
    }

    /**
     * Displays exit message.
     */
    public void onExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays number of tasks in the list.
     * @param count Number of tasks.
     */
    public void showTaskListCount(Integer count) {
        System.out.println("Now you have "+ count +" tasks in the list." );
    }
}
