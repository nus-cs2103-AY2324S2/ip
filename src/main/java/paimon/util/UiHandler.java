package paimon.util;

import java.util.Scanner;

import paimon.ChatException;
import paimon.task.TaskList;

/**
 * Manages user interactions by displaying messages.
 */
public class UiHandler {
    /**
     * Initializes a new instance of UiHandler with a Scanner
     * to read input from console.
     */
    public UiHandler() {}

    /**
     * Displays a message to the user. Standard format for messages.
     *
     * @param mainMessage The primary message to display.
     */

    private static String formatMessage(String mainMessage) {
        return "-------------------->\n" + mainMessage + "\n-------------------->";
    }

    /**
     * Displays a message to the user.
     * Use this when displaying tasks.
     *
     * @param mainMessage The main message to display.
     * @param subMessage  The sub message to provide additional details.
     */
    private static String formatMessage(String mainMessage, String subMessage) {
        return mainMessage + "\n-------------------->\n" + subMessage + "\n-------------------->";
    }
    /**
     * Displays a detailed message to the user.
     * Use this when displaying tasks along with a closing message.
     *
     * @param mainMessage    The main message to display.
     * @param subMessage     The sub message to provide additional details.
     * @param closingMessage The closing message to conclude the interaction.
     */
    private static String formatMessage(String mainMessage, String subMessage, String closingMessage) {
        return mainMessage + "\n-------------------->\n" + subMessage + "\n-------------------->\n" + closingMessage;
    }
    /**
     * Returns a help message detailing the syntax and commands supported.
     */
    public static String getHelpMessage() {
        return formatMessage("You can perform the following actions, make sure to follow the syntax!",
                "list : Lists all your Tasks\n" + "todo <task>: Adds a Task without any deadline\n"
                        + "deadline <task> /by <time>: Adds a task done before a time\n"
                        + "event<Task> /from <time> /to <time>: Adds a task with a time window\n"
                        + "mark/unmark <index>: Marks a Task as done. Index must be a number\n"
                        + "unmark <index>: Mark a Task as not done. Index must be a number\n"
                        + "delete <index>: Deletes a Task from your list!. Index must be a number\n"
                        + "find <keyword>: Lists tasks that contain keyword\n" + "bye : Exits the program");
    }

    /**
     * Notifies the user that a task has been added
     *
     * @param taskString Task that was added
     * @param tasksLeft The total number of tasks left after adding
     */
    public static String getAddTaskMessage(String taskString, int tasksLeft) {
        return formatMessage("Okay Traveller, I've added the following task!",
                taskString, "You have " + tasksLeft + " tasks in your list");
    }

    /**
     * Notifies the user that a task has been deleted and displays the remaining number of tasks.
     *
     * @param tasksLeft The total number of tasks left after deletion.
     */
    public static String getDeleteTaskMessage(int tasksLeft) {
        return formatMessage("Okay Traveller, I've deleted the task! You now have " + tasksLeft + " tasks remaining.");
    }

    /**
     * Returns the list of found tasks containing the keyword.
     *
     * @param taskString The list of tasks found
     */
    public static String getFoundTasksMessage(String taskString) {
        return formatMessage("Okay Traveller, here are the matching tasks", taskString);
    }


    /**
     * Notifies the user that a task's completion status has been updated.
     *
     * @param taskString The task whose status was updated.
     * @param isDone     The new completion status of the task (true if done, false otherwise).
     */
    public static String getMarkTaskMessage(String taskString, boolean isDone) {
        if (isDone) {
            return formatMessage("Okay Traveller, I've marked the task as done", taskString);
        } else {
            return formatMessage("Okay Traveller, I've unmarked this task", taskString);
        }
    }

    /**
     * Returns a greeting message to the user when the application starts.
     */
    public static String getGreetMessage() {
        return formatMessage("Greetings Traveller! I'm Paimon, your friendly guide!\nType help to see what I can do!");
    }

    /**
     * Returns a goodbye message to the user when the application is exited.
     */
    public static String getExitMessage() {
        return formatMessage("See you later Traveller!");
    }
    /**
     * Returns the list of tasks to the user. If the list is empty, it notifies the user accordingly.
     *
     * @param list The {@link TaskList} containing all current tasks.
     * @return String
     */
    public static String getListMessage(TaskList list) {
        if (list.getSize() == 0) {
            return "Your list is currently empty, add some tasks!";
        } else {
            return "Here is your list so far!\n-------------------->\n" + list + "\n-------------------->";
        }
    }

    /**
     * Returns an error message to the user.
     *
     * @param e The {@link ChatException} containing the error message to be displayed.
     * @return String
     */
    public static String getErrorMessage(ChatException e) {
        return e.getMessage();
    }
}
