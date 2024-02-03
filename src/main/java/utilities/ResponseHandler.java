package utilities;

import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A utility class for printing messages and handling interactions with the user.
 */
public class ResponseHandler {
    /**
     * The message to be printed or stored.
     */
    public String message;

    /**
     * Constructs a ResponseHandler with the given message.
     *
     * @param message The message to be printed or stored.
     */
    public ResponseHandler(String message) {
        this.message = message;
    }

    /**
     * Prints the stored message to the console.
     */
    public String printStored() {
        return this.message;
    }

    /**
     * Prints a personalized reply based on the given message.
     *
     * @param message The message to be analyzed and replied to.
     */
    public static String personalisedReply(String message) {
        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("byee", "You meant bye?");
        responseMap.put("byeee", "You meant bye?");
        responseMap.put("hi", "Hi again!");
        return responseMap.getOrDefault(message, message);
    }

    /**
     * Prints a line separator to the console.
     */
    public static String printLine() {
        return "____________________________________________________________";
    }

    /**
     * Prints a greeting message to the console.
     *
     * @param chatBotName The name of the chatbot.
     */
    public static String greeting(String chatBotName) {
        return "Hello! I'm " + chatBotName + "\nWhat can I do for you?";
    }

    /**
     * Prints a goodbye message to the console.
     */
    public static String bye() {
        return "Goodbye. Hope to see you again!";
    }

    /**
     * Prints a command to the console.
     *
     * @param command The command to be printed.
     */
    public static String commandPrint(String command) {
        return command;
    }

    /**
     * Prints information about a task and the total number of tasks to the console.
     *
     * @param task The task to be printed.
     * @param size The total number of tasks.
     */
    public static String commandPrint(Task task, int size) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
        //System.out.println(task);
        //System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints a list of tasks to the console.
     *
     * @param list The list of tasks to be printed.
     */
    public static String commandListPrint(ArrayList<Task> list) {
        StringBuilder stringOfAddedCommands = new StringBuilder();
        for (int i = 0; i < list.size(); i += 1) {
            stringOfAddedCommands.append(i + 1 + ". ");
            stringOfAddedCommands.append("\n");
            stringOfAddedCommands.append(list.get(i));
            if (i != list.size() - 1) {
                stringOfAddedCommands.append("\n");
            }
        }
        return stringOfAddedCommands.toString();
    }

    /**
     * Prints a message indicating the result of a mark action on a task.
     *
     * @param action The action performed ("mark" or "unmark").
     * @param task   The task on which the action was performed.
     */
    public static String markActionPrint(String action, Task task) {
        StringBuilder stringResponse = new StringBuilder();
        if (action.equals("mark")) {
            stringResponse.append("Nice! I've marked this task as done:\n");
        } else {
            stringResponse.append("OK, I've marked this task as not done yet:\n");
        }
        stringResponse.append(task);
        return stringResponse.toString();
    }

    /**
     * Prints an error message to the console based on the provided exception.
     *
     * @param e The exception containing the error message.
     */
    public static String errorPrinter(Exception e) {
        return e.getMessage();
    }

    /**
     * Prints information about a removed task and the updated total number of tasks to the console.
     *
     * @param task The task that was removed.
     * @param size The updated total number of tasks.
     */
    public static String removePrinter(Task task, int size) {
        StringBuilder stringResponse = new StringBuilder();
        stringResponse.append("Noted. I've removed this task:\n");
        stringResponse.append(task +"\n");
        stringResponse.append("Now you have " + size + " tasks in the list.");
        return stringResponse.toString();
    }

    public static String printFoundTasks(ArrayList<Task> listOfFoundTasks) {
        StringBuilder stringResponse = new StringBuilder();
        if (listOfFoundTasks.isEmpty()) {
            stringResponse.append("No such tasks in the list :(, try again!");
        } else {
            stringResponse.append("Found! Here they are!\n");
            for (int i = 0; i < listOfFoundTasks.size(); i += 1) {
                stringResponse.append((i+1 + "." + listOfFoundTasks.get(i)));
                if (i != listOfFoundTasks.size() - 1) {
                    stringResponse.append("\n");
                }
            }
        }
        return stringResponse.toString();
    }

}
