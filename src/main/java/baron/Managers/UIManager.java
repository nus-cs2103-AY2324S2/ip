package baron.Managers;

import baron.Models.Task;

import java.util.List;

public class UIManager {
    /**
     * Prints a simple separator for UI pruposes only
     */
    public static void printSeparator() {
        System.out.println("--------------------");
    }

    /**
     * Informs user of added content but does not add anything itself
     * @param task Task added
     * @param count count of tasks currently in list after modification
     */
    public static void add(Task task, int count) {
        printSeparator();
        System.out.println("Got it, I've added this task: \n  " + task);
        System.out.println("Now you have " + count + " tasks in the list");
        printSeparator();
    }

    /**
     * Informs user of deleted content
     * @param task Task added
     * @param count count of tasks currently in list after modification
     */
    public static void delete(Task task, int count) {
        printSeparator();
        System.out.println("Noted, I've removed this task: \n  " + task);
        System.out.println("Now you have " + count + " tasks in the list");
        printSeparator();
    }

    /**
     * Lists all tasks currently available in a pretty format
     */
    public static void list(List<Task> tasks) {
        printSeparator();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printSeparator();
    }
    
    public static void find(List<Task> tasks) {
        printSeparator();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printSeparator();
    }

    /**
     * Marks the specified task
     * @param task Task to mark
     * @param isDone mark whether task is done or not
     */
    public static void mark(Task task, boolean isDone) {
        printSeparator();
        String doneStr = isDone ? "done" : "not done";
        System.out.println("Okay, I've marked this task as " + doneStr + ": \n" + task);
        printSeparator();
    }
}
