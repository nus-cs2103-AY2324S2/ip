package duke.responses;

import duke.exceptions.ListOutOfBoundsException;
import duke.parser.Priority;
import duke.storage.Storage;
import duke.storage.Task;
import duke.storage.TaskList;


/**
 * The Responses class handles interactions with the user through the command-line interface.
 * It provides methods to display greetings, goodbyes, process user input, and perform various tasks.
 */
public class Responses {
    private static final String lines = "    ____________________________________________________________";
    private static final String name = "Wang";

    private final TaskList taskList;

    /**
     * Constructs a Responses object and initializes the associated TaskList.
     */
    public Responses() {
        this.taskList = new TaskList();
        Storage.start(taskList);
    }


    /**
     * Returns a greeting message when the Duke program starts.
     *
     * @return A string containing the greeting message.
     */
    public static String greeting() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("    Hello! I'm %s\n", name));
        stringBuilder.append("    What can I do for you?");

        return stringBuilder.toString();
    }

    /**
     * Returns a goodbye message when the Duke program ends.
     *
     * @return A string containing the goodbye message.
     */
    public static String goodbye() {
        return "    Bye. Hope to see you again soon!";
    }


    /**
     * Adds a task to the TaskList and displays a confirmation message.
     *
     * @param task The task to be added.
     * @return A string containing the confirmation message.
     */
    public String addItem(Task task) {
        this.taskList.add(task);
        return "    " + "Got it. I've added this task:\n" + "      " + task + "\n" + ""
                + String.format("    Now you have %d tasks in the list.", this.taskList.taskLength());
    }

    /**
     * Marks a task as done and displays a confirmation message.
     *
     * @param input The index of the task to be marked as done.
     * @return A string containing the confirmation message.
     * @throws ListOutOfBoundsException If the provided index is out of bounds.
     */
    public String markTaskUI(int input) throws ListOutOfBoundsException {
        StringBuilder stringBuilder = new StringBuilder();
        if (input < 0 || input > this.taskList.taskLength() - 1) {
            throw new ListOutOfBoundsException(String.format("%d", this.taskList.taskLength()));
        }
        stringBuilder.append("    Nice! I've marked this task as done:\n");
        stringBuilder.append(this.taskList.markTask(input));
        return stringBuilder.toString();
    }

    /**
     * Marks a task as not done and displays a confirmation message.
     *
     * @param input The index of the task to be marked as not done.
     * @return A string containing the confirmation message.
     * @throws ListOutOfBoundsException If the provided index is out of bounds.
     */
    public String unMarkTask(int input) throws ListOutOfBoundsException {
        StringBuilder stringBuilder = new StringBuilder();
        if (input < 0 || input > this.taskList.taskLength() - 1) {
            throw new ListOutOfBoundsException(String.format("%d", this.taskList.taskLength()));
        }
        stringBuilder.append("    OK, I've marked this task as not done yet:\n");
        stringBuilder.append(this.taskList.unMarkTask(input));
        return stringBuilder.toString();
    }

    /**
     * Updates the priority of a task at the specified index with the given priority.
     *
     * @param input    the index of the task to update
     * @param priority the new priority to set
     * @return a string indicating the successful update
     * @throws ListOutOfBoundsException if the input index is out of bounds
     */
    public String updatePriority(int input, Priority priority) throws ListOutOfBoundsException {
        StringBuilder stringBuilder = new StringBuilder();
        if (input < 0 || input > this.taskList.taskLength() - 1) {
            throw new ListOutOfBoundsException(String.format("%d", this.taskList.taskLength()));
        }
        stringBuilder.append("    OK, I've updated this task's Priority:\n");
        stringBuilder.append(this.taskList.updatePriority(input, priority));
        return stringBuilder.toString();
    }

    /**
     * Removes a task from the TaskList and returns a confirmation message.
     *
     * @param input The index of the task to be removed.
     * @return A string containing the confirmation message.
     * @throws ListOutOfBoundsException If the provided index is out of bounds.
     */
    public String removeTask(int input) throws ListOutOfBoundsException {

        StringBuilder stringBuilder = new StringBuilder();

        if (input < 0 || input > this.taskList.taskLength() - 1) {
            throw new ListOutOfBoundsException(String.format("%d", this.taskList.taskLength()));
        }

        stringBuilder.append("    Noted. I've removed this task:\n");
        stringBuilder.append(this.taskList.remove(input));
        stringBuilder.append(String.format("\n    Now you have %d tasks in the list.", this.taskList.taskLength()));

        return stringBuilder.toString();
    }

    /**
     * Returns the tasks matching the specified key using the taskList's find method
     *
     * @param key The keyword or item to search for in the tasks.
     * @return A string containing the tasks matching the specified key.
     */
    public String findTaskUI(String key) {
        return this.taskList.find(key);
    }

    /**
     * Returns the list of tasks in the TaskList.
     */
    public String listItems() {
        return taskList.toString();
    }


    /**
     * Returns an error message.
     *
     * @param error The error message to be displayed.
     */
    public static String error(String error) {
        return error;
    }


}
