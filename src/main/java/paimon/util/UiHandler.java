package paimon.util;


import paimon.ChatException;
import paimon.task.TaskList;

import java.util.Scanner;

/**
 * Manages user interactions by displaying messages.
 */
public class UiHandler {
    private final Scanner scanner;

    /**
     * Initializes a new instance of UiHandler with a Scanner
     * to read input from console.
     */
    public UiHandler() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a message to the user. Standard format for messages.
     *
     * @param mainMessage The primary message to display.
     */

    private static void displayMessage(String mainMessage) {
        System.out.println("-------------------->");
        System.out.println(mainMessage);
        System.out.println("-------------------->");
    }

    /**
     * Displays a message to the user.
     * Use this when displaying tasks.
     *
     * @param mainMessage The main message to display.
     * @param subMessage  The sub message to provide additional details.
     */
    private static void displayMessage(String mainMessage, String subMessage) {
        System.out.println(mainMessage);
        System.out.println("-------------------->");
        System.out.println(subMessage);
        System.out.println("-------------------->");
    }

    /**
     * Displays a detailed message to the user.
     * Use this when displaying tasks along with a closing message.
     *
     * @param mainMessage    The main message to display.
     * @param subMessage     The sub message to provide additional details.
     * @param closingMessage The closing message to conclude the interaction.
     */
    private static void displayMessage(String mainMessage, String subMessage, String closingMessage) {
        System.out.println(mainMessage);
        System.out.println("-------------------->");
        System.out.println(subMessage);
        System.out.println("-------------------->");
        System.out.println(closingMessage);
    }

    /**
     * Reads a command from the user via the console.
     *
     * @return The command entered by the user as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a help message detailing the syntax and commands supported.
     */
    public void displayHelpMessage() {
        displayMessage("You can perform the following actions, make sure to follow the syntax!", "list : Lists all your Tasks\n" + "todo <task>: Adds a Task without any deadline\n" + "deadline <task> /by <time>: Adds a task done before a time\n" + "event<Task> /from <time> /to <time>: Adds a task with a time window\n" + "mark/unmark <index>: Marks a Task as done. Index must be a number\n" + "unmark <index>: Mark a Task as not done. Index must be a number\n" + "delete <index>: Deletes a Task from your list!. Index must be a number\n" + "find <keyword>: Lists tasks that contain keyword\n" + "bye : Exits the program");
    }

    public void displayAddTaskMessage(String taskString, int tasksLeft) {
        displayMessage("Okay Traveller, I've added the following task!", taskString, "You have " + tasksLeft + " tasks in your list");
    }

    /**
     * Notifies the user that a task has been deleted and displays the remaining number of tasks.
     *
     * @param tasksLeft The total number of tasks left after deletion.
     */
    public void displayDeleteTaskMessage(int tasksLeft) {
        displayMessage("Okay Traveller, I've deleted the task! You now have " + tasksLeft + " tasks remaining.");
    }

    /**
     * Displays the list of found tasks containing the keyword.
     *
     * @param taskString The list of tasks found
     */
    public void displayFoundTasksMessage(String taskString) {
        displayMessage("Okay Traveller, here are the matching tasks", taskString);
    }


    /**
     * Notifies the user that a task's completion status has been updated.
     *
     * @param taskString The task whose status was updated.
     * @param isDone     The new completion status of the task (true if done, false otherwise).
     */
    public void displayMarkTaskMessage(String taskString, boolean isDone) {
        if (isDone) {
            displayMessage("Okay Traveller, I've marked the task as done", taskString);
        } else {
            displayMessage("Okay Traveller, I've unmarked this task", taskString);
        }
    }

    /**
     * Displays a greeting message to the user when the application starts.
     */
    public void displayGreetMessage() {
        displayMessage("Greetings Traveller! I'm Paimon, your friendly guide!\nType help to see what I can do!");
    }

    /**
     * Displays a goodbye message to the user when the application is exited.
     */
    public void displayExitMessage() {
        displayMessage("See you later Traveller!");
    }

    /**
     * Displays the list of tasks to the user. If the list is empty, it notifies the user accordingly.
     *
     * @param list The {@link TaskList} containing all current tasks.
     */
    public void displayListMessage(TaskList list) {
        if (list.getSize() == 0) {
            System.out.println("Your list is currently empty, add some tasks!");
        } else {
            System.out.println("Here is your list so far!");
            System.out.println("-------------------->");
            System.out.println(list);
            System.out.println("-------------------->");
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param e The {@link ChatException} containing the error message to be displayed.
     */
    public void displayError(ChatException e) {
        System.out.println(e.getMessage());
    }
}
