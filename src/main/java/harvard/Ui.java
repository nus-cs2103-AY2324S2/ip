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
    public void printAddTask(Task task, int tasksLeft) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasksLeft + " tasks in the list.");
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints a message confirming the deletion of a task.
     *
     * @param task The task that was removed.
     * @param tasksLeft The number of tasks left in the list.
     */
    public void printDeleteTask(Task task, int tasksLeft) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasksLeft + " tasks in the list.");
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints a message confirming the marking of a task as done.
     *
     * @param task The task that was marked as done.
     */
    public void printMark(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints a message confirming the marking of a task as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printUnmark(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints a message for an unrecognized command.
     */
    public void printUnrecognisedCommand() {
        System.out.println("____________________________________________________________");
        System.out.println("Bro... Idk what that is man.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The TaskList containing the tasks to be printed.
     */
    public void printTasks(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            try {
                System.out.println(i + 1 + ". " + tasks.printString(i));
            } catch (HarvardException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints the tasks in the given TaskList that match the search criteria.
     * This method prints each matching task along with its index in the list.
     * If there are no matching tasks, it prints a message indicating so.
     *
     * @param tasks the TaskList containing tasks to be printed
     */
    public void printFindTasks(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            try {
                System.out.println(i + 1 + ". " + tasks.printString(i));
            } catch (HarvardException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("____________________________________________________________\n");
    }

}
