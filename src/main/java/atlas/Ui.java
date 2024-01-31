package atlas;

import atlas.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles all user interactions.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user.
     */
    public void showGreeting() {
        System.out.println("Hello! I'm Atlas");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The line of input as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message indicating that a task has been added and how many tasks are in the list.
     *
     * @param tasks The TaskList from which to retrieve the newly added task.
     */
    public void showTaskAdded(TaskList tasks) {
        ArrayList<Task> al = tasks.getTasks();
        System.out.println("Got it. I've added this task:\n  " + al.get(al.size() - 1));
        System.out.println("Now you have " + al.size() + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been deleted and how many remaining tasks in the list.
     *
     * @param tasks The TaskList from which to retrieve the deleted task.
     * @param index The index of the deleted task.
     */
    public void showTaskDeleted(TaskList tasks, int index) {
        ArrayList<Task> al = tasks.getTasks();
        System.out.println("Got it. I've removed this task:\n  " + al.get(index));
        System.out.println("Now you have " + (al.size() - 1) + " tasks in the list.");
    }

    /**
     * Displays all tasks that are scheduled on a given date.
     *
     * @param tasks The TaskList from which to retrieve tasks.
     * @param date  The date for which tasks should be displayed.
     */
    public void showTasksOnDate(TaskList tasks, LocalDate date) {
        ArrayList<Task> al = tasks.getTasksOnDate(date);
        if (al.isEmpty()) {
            System.out.println("No tasks found on " + date);
        } else {
            System.out.println("Tasks on " + date + ":");
            for (Task task : al) {
                System.out.println(task);
            }
        }
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param tasks The TaskList from which to retrieve the marked task.
     * @param index The index of the marked task.
     */
    public void showMark(TaskList tasks, int index) {
        ArrayList<Task> al = tasks.getTasks();
        System.out.println("Nice! I've marked this task as done:");
        String str = al.get(index).toString();
        System.out.println(str);
    }

    /**
     * Displays a message indicating that a task has been unmarked.
     *
     * @param tasks The TaskList from which to retrieve the unmarked task.
     * @param index The index of the unmarked task.
     */
    public void showunMark(TaskList tasks, int index) {
        ArrayList<Task> al = tasks.getTasks();
        System.out.println("OK, I've marked this task as not done yet");
        String str = al.get(index).toString();
        System.out.println(str);
    }

    /**
     * Displays all tasks in the list.
     *
     * @param tasks The ArrayList of tasks to be displayed.
     */
    public void showAllTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }
}
