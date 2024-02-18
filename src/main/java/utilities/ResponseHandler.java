package utilities;

import java.util.ArrayList;

import tasks.Task;

/**
 * A utility class for returning responses as a String.
 */
public class ResponseHandler {
    /**
     * Generates a greeting message.
     *
     * @param chatBotName The name of the chatbot.
     * @return A greeting message.
     */
    public static String greeting(String chatBotName) {
        return "I might be " + chatBotName
                + "\nWhat can I do for you?"
                + "\nYou can start by trying help and typing the command to see the format!";
    }

    /**
     * Generates a goodbye message.
     *
     * @return A goodbye message.
     */
    public static String bye() {
        return "Goodbye. Hope to see you again!";
    }

    /**
     * Generates a message with information about a task and the total number of tasks.
     *
     * @param task The task to be printed.
     * @param size The total number of tasks.
     * @return A message with task information.
     */
    public static String commandPrint(Task task, int size) {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + size
                + " tasks in the list.";
    }

    /**
     * Generates a message with a list of tasks.
     *
     * @param list The list of tasks to be printed.
     * @return A message with the list of tasks.
     */
    public static String commandListPrint(ArrayList<Task> list) {
        if (list.isEmpty()) {
            return "Task list is empty :(";
        }
        StringBuilder stringOfAddedCommands = new StringBuilder();
        for (int i = 0; i < list.size(); i += 1) {
            stringOfAddedCommands.append(i + 1).append(". ");
            stringOfAddedCommands.append("\n");
            stringOfAddedCommands.append(list.get(i));
            if (i != list.size() - 1) {
                stringOfAddedCommands.append("\n");
            }
        }
        return stringOfAddedCommands.toString();
    }

    /**
     * Generates a message indicating the result of a mark action on a task.
     *
     * @param action The action performed ("mark" or "unmark").
     * @param task   The task on which the action was performed.
     * @return A message with the mark action result.
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
     * Generates an error message based on the provided exception.
     *
     * @param e The exception containing the error message.
     * @return An error message.
     */
    public static String errorPrinter(Exception e) {
        return e.getMessage();
    }

    /**
     * Generates a message with information about a removed task and the updated total number of tasks.
     *
     * @param task The task that was removed.
     * @param size The updated total number of tasks.
     * @return A message with information about the removed task.
     */
    public static String removePrinter(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Generates a message with information about found tasks.
     *
     * @param listOfFoundTasks The list of found tasks.
     * @return A message with information about found tasks.
     */
    public static String printFoundTasks(ArrayList<Task> listOfFoundTasks) {
        StringBuilder stringResponse = new StringBuilder();
        if (listOfFoundTasks.isEmpty()) {
            stringResponse.append("No such tasks in the list :(, try again!");
        } else {
            stringResponse.append("Found! Here they are!\n");
            for (int i = 0; i < listOfFoundTasks.size(); i += 1) {
                stringResponse.append(i + 1).append(".").append(listOfFoundTasks.get(i));
                if (i != listOfFoundTasks.size() - 1) {
                    stringResponse.append("\n");
                }
            }
        }
        return stringResponse.toString();
    }
}
