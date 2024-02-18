package lite.util;

import lite.task.Task;
import lite.task.TaskList;

public class Printer {

    /**
     * Prints a message notifying that a task has been removed
     * @param tasks List of tasks
     * @param index Index of task to remove from
     */
    public static String printRemoveTask(TaskList tasks, int index) {
        return "Noted. I've removed this task:\n" +
                tasks.get(index) + "\n" +
                "Now you have " + (tasks.size() - 1) + " tasks in the list.";
    }


    /**
     * Prints a message notifying that a task has been added
     * @param tasks List of tasks
     * @param task Task to be added
     */
    public static String printTask(TaskList tasks, Task task) {
        return "Got it. I've added this task: \n" +
                task + "\n" + "Now you have " + tasks.size() + " tasks in the list";
    }

    /**
     * Prints out a message notifying that a task has been unmarked
     * @param task Task being unmarked
     */
    public static String printUnmark(Task task) {
        String output = "This task has been unmarked!\n" + task.unmark();
        return output;
    }


    /**
     * Prints out a message notifying that a task has been marked
     * @param task Task being marked
     */
    public static String printMark(Task task) {
        String output = "This task has been marked as done!\n" + task.mark();
        return output;
    }

    /**
     * Prints out all the tasks that contains the input
     * @param tasks List of tasks
     */
    public static String printFound(TaskList tasks) {
        String output = "Here are the matching tasks in your list:\n";
        output += tasks.taskString();
        return output;
    }

    /**
     * Prints out a message if no tasks contains the input
     */
    public static String printNotFound() {
        return "There are no tasks that correspond with your input";
    }

    /**
     * Prints out a message if the task is already in the list
     */
    public static String printDuplicateFound() {
        return "This task is already in your Task List.";
    }
}
