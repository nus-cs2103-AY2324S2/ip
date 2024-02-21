package kwuntalk;

import kwuntalk.exception.KwunTalkException;
import kwuntalk.task.Task;

import java.util.Scanner;


/**
 * Represents the user interface which deals with the interactions with the user.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);


    /**
     * Reads user input.
     *
     * @return User input.
     */
    public String readInput() {
        return this.sc.nextLine();
    }


    /**
     * Closes the input Scanner.
     */
    public void close() {
        this.sc.close();
    }


    /**
     * Shows the greeting message to the user.
     */
    public String showGreet() {
        return "Hello! I'm KwunTalk!\nWhat can I do for you?\n";
    }


    /**
     * Shows the goodbye message to the user.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!\n";
    }


    /**
     * Shows the lists of tasks.
     *
     * @param taskList List of tasks.
     */
    public String listTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "Your list is currently empty.\n";

        } else {
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= taskList.getLength(); i++) {
                sb.append(String.format("%d. %s\n", i, taskList.get(i)));
            }
            return "Here are the tasks in your list:\n" + sb;
        }
    }


    /**
     * Shows the message when a task is deleted from the list.
     *
     * @param task Task that was deleted.
     * @param taskTotal Total number of tasks in the list after deleting.
     */
    public String showDeleteTask(Task task, int taskTotal) {
        return String.format("OK. I've deleted this task:\n%s\nNow you have %s tasks in the list.\n",
                task, taskTotal);
    }


    /**
     * Shows the message when a task is marked as done.
     *
     * @param task Task to be marked.
     */
    public String showMarkTask(Task task) {
        return String.format("Nice! I've marked this task as done:\n%s\n", task);
    }


    /**
     * Shows the message when a task is unmarked.
     *
     * @param task Task to be unmarked.
     */
    public String showUnmarkTask(Task task) {
        return String.format("OK, I've marked this task as not done yet:\n%s\n", task);
    }


    /**
     * Shows the message when a task is added to the list.
     *
     * @param task Task to be added.
     * @param taskTotal Total number of tasks in the list after adding.
     */
    public String showAddTask(Task task, int taskTotal) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %s tasks in the list.\n",
                task, taskTotal);
    }


    /**
     * Shows the error message when an exception is thrown
     *
     * @param error Exception to be handled.
     */
    public String showError(KwunTalkException error) {
        return error.toString();
    }


    /**
     * Shows the message when finding a task.
     *
     * @param filteredList Filtered list of tasks.
     */
    public String showFindTask(TaskList filteredList) {
        if (filteredList.isEmpty()) {
            return "There are no matching tasks in your list.\n";

        } else {
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= filteredList.getLength(); i++) {
                sb.append(String.format("%d. %s\n", i, filteredList.get(i)));
            }
            return "Here are the matching tasks in your list:\n" + sb;
        }
    }
}
