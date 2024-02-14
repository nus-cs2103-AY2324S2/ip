package lite.util;

import lite.task.Task;
import lite.task.TaskList;

public class Printer {

    /**
     * Prints a horizontal line for formatting
     */
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message notifying that a task has been removed
     * @param tasks List of tasks
     * @param index Index of task to remove from
     */
    public static void printRemoveTask(TaskList tasks, int index) {
        System.out.println("Noted. I've removed this lite.task:\n" +
                tasks.get(index) + "\n" +
                "Now you have " + (tasks.size() - 1) + " tasks in the tasks.");
    }


    /**
     * Prints a message notifying that a task has been added
     * @param tasks List of tasks
     * @param task Task to be added
     */
    public static void printTask(TaskList tasks, Task task) {
        System.out.println("Got it. I've added this lite.task: ");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the tasks");
    }

    /**
     * Prints out a message notifying that a task has been unmarked
     * @param task Task being unmarked
     */
    public static void printUnmark(Task task) {
        System.out.println(task.unmark());
    }


    /**
     * Prints out a message notifying that a task has been marked
     * @param task Task being marked
     */
    public static void printMark(Task task) {
        System.out.println(task.mark());
    }

    public static void printFound(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        tasks.taskString();
    }

    public static void printNotFound() {
        System.out.println("There are no tasks that correspond with your input");
    }
}
