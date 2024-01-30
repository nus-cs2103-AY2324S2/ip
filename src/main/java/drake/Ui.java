package drake;
import java.util.ArrayList;
import drake.task.Task;
import drake.task.TaskList;

/**
 * Handles UI operations for the chatbot.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" What's up everyone. I'm Drake.");
        System.out.println(" How can I help?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println(" See you later, alligator! ");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a task along with a custom message and the total number of tasks.
     *
     * @param message The custom message to display before the task.
     * @param task    The task to be displayed.
     * @param size    The total number of tasks in the list.
     */
    public void showTask(String message, Task task, int size) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("You asked for the tasks in your list? Here:");
        ArrayList<Task> taskList = tasks.getTasks();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message confirming the addition of a task, the task itself, and the new total number of tasks.
     *
     * @param task The task that has been added.
     * @param size The new total number of tasks in the list.
     */
    public void showAddTask(Task task, int size) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that has been marked.
     */
    public void showMarkTask(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Cool. I now declare this task marked as, done:");
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The task that has been unmarked.
     */
    public void showUnmarkTask(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message confirming the deletion of a task, the task itself, and the new total number of tasks.
     *
     * @param task The task that has been deleted.
     * @param size The new total number of tasks in the list.
     */
    public void showDeleteTask(Task task, int size) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

}