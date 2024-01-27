package utilities;

import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A utility class for printing messages and handling interactions with the user.
 */
public class MessagePrinter {
    /**
     * The message to be printed or stored.
     */
    public String message;

    /**
     * Constructs a MessagePrinter with the given message.
     *
     * @param message The message to be printed or stored.
     */
    public MessagePrinter(String message) {
        this.message = message;
    }

    /**
     * Prints the stored message to the console.
     */
    public void printStored() {
        System.out.println(this.message);
    }

    /**
     * Prints a personalized reply based on the given message.
     *
     * @param message The message to be analyzed and replied to.
     */
    public static void personalisedReply(String message) {
        MessagePrinter.printLine();
        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("byee", "You meant bye?");
        responseMap.put("byeee", "You meant bye?");
        responseMap.put("hi", "Hi again!");
        if (responseMap.containsKey(message)) {
            System.out.println(responseMap.get(message));
        } else {
            System.out.println(message);
        }
        MessagePrinter.printLine();
    }

    /**
     * Prints a line separator to the console.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints a greeting message to the console.
     *
     * @param chatBotName The name of the chat bot.
     */
    public static void greeting(String chatBotName) {
        MessagePrinter.printLine();
        System.out.println("Hello! I'm " + chatBotName + "\nWhat can I do for you?");
        MessagePrinter.printLine();
    }

    /**
     * Prints a goodbye message to the console.
     */
    public static void bye() {
        MessagePrinter.printLine();
        System.out.println("Goodbye. Hope to see you again!");
        MessagePrinter.printLine();
    }

    /**
     * Prints a command to the console.
     *
     * @param command The command to be printed.
     */
    public static void commandPrint(String command) {
        MessagePrinter.printLine();
        System.out.println(command);
        MessagePrinter.printLine();
    }

    /**
     * Prints information about a task and the total number of tasks to the console.
     *
     * @param task The task to be printed.
     * @param size The total number of tasks.
     */
    public static void commandPrint(Task task, int size) {
        MessagePrinter.printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        MessagePrinter.printLine();
    }

    /**
     * Prints a list of tasks to the console.
     *
     * @param list The list of tasks to be printed.
     */
    public static void commandListPrint(ArrayList<Task> list) {
        MessagePrinter.printLine();
        for (int i = 0; i < list.size(); i += 1) {
            System.out.print(i + 1 + ". ");
            System.out.println(list.get(i));
        }
        MessagePrinter.printLine();
    }

    /**
     * Prints a message indicating the result of a mark action on a task.
     *
     * @param action The action performed ("mark" or "unmark").
     * @param task   The task on which the action was performed.
     */
    public static void markActionPrint(String action, Task task) {
        MessagePrinter.printLine();
        if (action.equals("mark")) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(task);
        MessagePrinter.printLine();
    }

    /**
     * Prints an error message to the console based on the provided exception.
     *
     * @param e The exception containing the error message.
     */
    public static void errorPrinter(Exception e) {
        MessagePrinter.printLine();
        System.out.println(e.getMessage());
        MessagePrinter.printLine();
    }

    /**
     * Prints information about a removed task and the updated total number of tasks to the console.
     *
     * @param task The task that was removed.
     * @param size The updated total number of tasks.
     */
    public static void removePrinter(Task task, int size) {
        size -= 1;
        MessagePrinter.printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        MessagePrinter.printLine();
    }
}
