package harvard;

import harvard.exceptions.HarvardException;
import harvard.tasks.Task;

/**
 * The Ui class is responsible for handling the user interface of the Harvard application.
 * It provides methods to display messages and interact with the user.
 */
public class Ui {

    /**
     * Constructs a new Ui instance.
     */
    public Ui() {
    }

    /**
     * Displays a welcome message when the application starts.
     */
    public void showWelcome() {
        String initial = "____________________________________________________________\n"
                + "Hello! I'm Harvard\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(initial);
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public void showGoodbye() {
        String exit = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println(exit);
    }

    /**
     * Prints a message confirming the addition of a task.
     *
     * @param task The task that was added.
     * @param tasksLeft The number of tasks left in the list.
     */
    public String printAddTask(Task task, int tasksLeft) {
        String str = "Got it. I've added this task:\n";
        str += task.toString();
        str += "\nNow you have " + tasksLeft + " tasks in the list.\n";
        return str;
    }

    /**
     * Prints a message confirming the deletion of a task.
     *
     * @param task The task that was removed.
     * @param tasksLeft The number of tasks left in the list.
     */
    public String printDeleteTask(Task task, int tasksLeft) {
        String str = "Noted. I've removed this task:\n";
        str += task.toString();
        str += "\nNow you have " + tasksLeft + " tasks in the list.";
        return str;
    }

    /**
     * Prints a message confirming the marking of a task as done.
     *
     * @param task The task that was marked as done.
     */
    public String printMark(Task task) {
        String str = "Nice! I've marked this task as done:\n";
        str += task.toString();
        return str;
    }

    /**
     * Prints a message confirming the marking of a task as not done.
     *
     * @param task The task that was marked as not done.
     */
    public String printUnmark(Task task) {
        String str = "OK, I've marked this task as not done yet:\n";
        str += task.toString();
        return str;
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The TaskList containing the tasks to be printed.
     */
    public String printTasks(TaskList tasks) {
        String str = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            try {
                str += i + 1 + ". " + tasks.printString(i) + "\n";
            } catch (HarvardException e) {
                str += e.getMessage();
            }
        }
        return str;
    }

    /**
     * Prints the tasks in the given TaskList that match the search criteria.
     * This method prints each matching task along with its index in the list.
     * If there are no matching tasks, it prints a message indicating so.
     *
     * @param tasks the TaskList containing tasks to be printed
     */
    public String printFindTasks(TaskList tasks) {
        String str = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            try {
                str += i + 1 + ". " + tasks.printString(i) + "\n";
            } catch (HarvardException e) {
                str += e.getMessage();
            }
        }
        return str;
    }
}
