import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LINE_SEPARATOR = "____________________________________________________________";

    public static void showWelcomeMessage() {
        System.out.println("Hello! I'm RATZCHAT");
        System.out.println("How can I help you today?");
        printLine();
    }

    public static void showByeMessage() {
        System.out.println("BYEBYE. Thank you for using RATZCHAT!");
        printLine();
    }

    public static void showErrorMessage(String message) {
        System.out.println("Error: " + message);
        printLine();
    }

    public static void printLine() {
        System.out.println(LINE_SEPARATOR);
    }

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void showTaskList(ArrayList<Task> tasks) {
        System.out.println("These are your to-dos: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printLine();
    }

    public static void showMarkedAsDone(Task task) {
        System.out.println("I've marked this task as done:\n  " + task);
    }

    public static void showUnmarkedTask(Task task) {
        System.out.println("I've unmarked this task! It is now not done yet:\n  " + task);
    }

    public static void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Ok! I've added this task: " + task);
        System.out.println("Now you have " + totalTasks + " tasks in your list.");
    }

    public static void showTaskRemoved(Task removedTask, int totalTasks) {
        System.out.println("Ok! I have removed this task from your list:\n  " + removedTask);
        System.out.println("Now you have " + totalTasks + " tasks in your list.");
    }
}