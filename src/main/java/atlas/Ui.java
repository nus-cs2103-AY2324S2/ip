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
    public static String showGreeting() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello! I'm Atlas\n");
        sb.append("What can I do for you?");
        return sb.toString();
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showGoodbye() {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        return message;
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
    public String showTaskAdded(TaskList tasks) {
        ArrayList<Task> al = tasks.getTasks();
        assert !al.isEmpty() : "Task list should not be empty after adding";
        return ("Got it. I've added this task:\n  " + al.get(al.size() - 1));

    }

    /**
     * Displays a message indicating that a task has been deleted and how many remaining tasks in the list.
     *
     * @param task The deleted task to be shown.
     */
    public String showTaskDeleted(Task task) {
        return ("Got it. I've removed this task:\n  " + task);
    }

    /**
     * Displays all tasks that are scheduled on a given date.
     *
     * @param tasks The TaskList from which to retrieve tasks.
     * @param date  The date for which tasks should be displayed.
     */
    public String showTasksOnDate(TaskList tasks, LocalDate date) {
        ArrayList<Task> al = tasks.getTasksOnDate(date);
        if (al.isEmpty()) {
            return ("No tasks found on " + date);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Tasks on ").append(date).append(":\n");
            int i = 1;
            for (Task task : al) {
                sb.append(i).append(". ").append(task).append("\n");
                i++;
            }
            return sb.toString();
        }
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param tasks The TaskList from which to retrieve the marked task.
     * @param index The index of the marked task.
     */
    public String showMark(TaskList tasks, int index) {
        ArrayList<Task> al = tasks.getTasks();
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        String str = al.get(index).toString();
        sb.append(str);
        return sb.toString();
    }

    /**
     * Displays a message indicating that a task has been unmarked.
     *
     * @param tasks The TaskList from which to retrieve the unmarked task.
     * @param index The index of the unmarked task.
     */
    public String showunMark(TaskList tasks, int index) {
        ArrayList<Task> al = tasks.getTasks();
        StringBuilder sb = new StringBuilder();
        sb.append("OK, I've marked this task as not done yet:\n");
        String str = al.get(index).toString();
        sb.append(str);
        return sb.toString();
    }

    /**
     * Displays all tasks in the list.
     *
     * @param tasks The ArrayList of tasks to be displayed.
     */
    public String showAllTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return ("There are no tasks in your list.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * Takes in a list of tasks and returns a string matching the tasks.
     *
     * @param tasks Tasks found in the TaskList.
     * @return A string of tasks that match.
     */
    public String showMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return ("No matching tasks found");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }

    public String showTaskPriority(TaskList tasks, int taskIndex) {
        ArrayList<Task> al = tasks.getTasks();
        StringBuilder sb = new StringBuilder();
        sb.append("OK! I've changed the priority of this task\n");
        String str = al.get(taskIndex).toString();
        sb.append(str);
        return sb.toString();

    }
}
