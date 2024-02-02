package baron.Managers;

import baron.Models.Task;

import java.util.List;

public class UIManager {
    public static void printSeparator() {
        System.out.println("--------------------");
    }

    public static void add(Task task, int count) {
        printSeparator();
        System.out.println("Got it, I've added this task: \n  " + task);
        System.out.println("Now you have " + count + " tasks in the list");
        printSeparator();
    }

    public static void delete(Task task, int count) {
        printSeparator();
        System.out.println("Noted, I've removed this task: \n  " + task);
        System.out.println("Now you have " + count + " tasks in the list");
        printSeparator();
    }

    public static void list(List<Task> tasks) {
        printSeparator();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printSeparator();
    }

    public static void mark(Task task, boolean isDone) {
        printSeparator();
        String doneStr = isDone ? "done" : "not done";
        System.out.println("Okay, I've marked this task as " + doneStr + ": \n" + task);
        printSeparator();
    }
}
