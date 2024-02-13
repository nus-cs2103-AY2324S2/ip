package lite.util;

import lite.task.Task;
import lite.task.TaskList;

public class Printer {

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void removeTask(TaskList tasks, int index) {
        System.out.println("Noted. I've removed this lite.task:\n" +
                tasks.get(index) + "\n" +
                "Now you have " + (tasks.size() - 1) + " tasks in the tasks.");
    }

    public static void deleteException(TaskList tasks) {
        System.out.println("Please input a valid index. \n"
                + "The correct format is delete <index>. \n"
                + "The minimum index is 1 and the maximum index is " + tasks.size());
    }

    public static void printTask(TaskList tasks, Task task) {
        System.out.println("Got it. I've added this lite.task: ");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the tasks");
    }

    public static void toDoException() {
        System.out.println("Please give a name for the todo lite.task. \n +" +
                "The correct format is todo <description>");
    }

    public static void deadlineException() {
        System.out.println("Invalid input. \n" +
                "Please follow the format of: deadline <description> /by <date>");
    }

    public static void eventException() {
        System.out.println("Invalid input. \n" +
                "Please follow the format: event <description> /from <date> /to <date>");
    }

    public static void printUnmark(Task task) {
        System.out.println(task.unmark());
    }

    public static void unmarkException(TaskList tasks) {
        System.out.println("Please input a valid index. \n"
                + "The correct format is unmark <index>. \n"
                + "The minimum index is 1 and the maximum index is " + tasks.size());
    }

    public static void printMark(Task task) {
        System.out.println(task.mark());
    }

    public static void markException(TaskList tasks) {
        System.out.println("Please input a valid index. \n"
                + "The correct format is mark <index>. \n"
                + "The minimum index is 1 and the maximum index is " + tasks.size());
    }
}
