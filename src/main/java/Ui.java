import java.util.Scanner;

/**
 * Represents the user interface of the ChatBro.
 */
public class Ui {
    public static void printWelcome() {
        System.out.println("_________________________\n"
                + " __  __       __\n"
                + " \\ \\/ /__    / /\n"
                + "  \\  / _ \\  /_/ \n"
                + "  /_/\\___/ (_)\n\n"
                + "I'm ChatBro!\n"
                + "What can I do for you bro?\n"
                + "Use the available commands: list, bye, mark, unmark, delete, OR\n"
                + "create a new task (todo, deadline, event) to store in your list bro.\n"
                + "_________________________\n");
    }
    public static void printLine() {
        System.out.println("_________________________");
    }
    public static void printMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }
    public static void printBye() {
        printMessage("I have saved your tasks. Hasta la vista bro!");
    }

    public static void printFormatError(String format) {
        printMessage("Hey bro, make sure to follow the format:\n" + format);
    }
    public static void printAddMessage(Task task) {
        printMessage("Got it bro. I've added this task:\n" + task.toString()
                + "\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.");
    }
    public static void printDeleteMessage(Task task) {
        printMessage("Noted bro. I've removed this task:\n" + task.toString()
                + "\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.");
    }
    public static void printDoesNotExistError() {
        printMessage("Sorry bro, this task does not exist in your list.");
    }
    public static void printMarkMessage(Task task) {
        printMessage("Got it bro! I've marked this task as done:\n" + task.toString());
    }
    public static void printUnmarkMessage(Task task) {
        printMessage("Got it bro! I've marked this task as undone:\n" + task.toString());
    }
    public static void printList() {
        System.out.println("Here are the tasks in your list bro:");
        printLine();
        for (int i = 1; i <= 100; i++) {
            if (TaskManager.getList().get(i) == null) {
                break;
            }
            System.out.println(i + "." + TaskManager.getList().get(i).toString());
        }
        printLine();
    }
}
