package duke.responses;

import duke.exceptions.ListOutofBoundsException;
import duke.storage.Storage;
import duke.storage.Task;
import duke.storage.TaskList;


/**
 * The UI class handles interactions with the user through the command-line interface.
 * It displays greetings, goodbyes, and processes user input to perform various tasks.
 */
public class Responses {
    private static final String lines = "    ____________________________________________________________";
    private static final String name = "Wang";

    private final TaskList taskList;

    /**
     * Constructs a UI object and initializes the associated TaskList.
     */
    public Responses() {
        this.taskList = new TaskList();
        Storage.start(taskList);
    }

    /**
     * Displays a greeting message when the Duke program starts.
     */
    public static String greeting() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("    Hello! I'm %s\n", name));
        stringBuilder.append("    What can I do for you?");

        return stringBuilder.toString();
    }

    /**
     * Displays a goodbye message when the Duke program ends.
     */
    public static String goodbye() {
        return "    Bye. Hope to see you again soon!";
    }


    /**
     * Adds a task to the TaskList and displays a confirmation message.
     *
     * @param task The task to be added.
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
     * @throws ListOutofBoundsException If the provided index is out of bounds.
     */
    public String markTaskUI(int input) throws ListOutofBoundsException {
        StringBuilder stringBuilder = new StringBuilder();
        if (input < 0 || input > this.taskList.taskLength() - 1) {
            throw new ListOutofBoundsException(String.format("%d", this.taskList.taskLength()));
        }
        stringBuilder.append("    Nice! I've marked this task as done:");
        stringBuilder.append(this.taskList.markTask(input));
        return stringBuilder.toString();
    }

    /**
     * Marks a task as not done and displays a confirmation message.
     *
     * @param input The index of the task to be marked as not done.
     * @throws ListOutofBoundsException If the provided index is out of bounds.
     */
    public String unMarkTask(int input) throws ListOutofBoundsException {
        StringBuilder stringBuilder = new StringBuilder();
        if (input < 0 || input > this.taskList.taskLength() - 1) {
            throw new ListOutofBoundsException(String.format("%d", this.taskList.taskLength()));
        }
        stringBuilder.append("    OK, I've marked this task as not done yet:\n");
        stringBuilder.append(this.taskList.unMarkTask(input));
        return stringBuilder.toString();
    }

    /**
     * Removes a task from the TaskList and displays a confirmation message.
     *
     * @param input The index of the task to be removed.
     * @throws ListOutofBoundsException If the provided index is out of bounds.
     */
    public String removeTask(int input) throws ListOutofBoundsException {

        StringBuilder stringBuilder = new StringBuilder();

        if (input < 0 || input > this.taskList.taskLength() - 1) {
            throw new ListOutofBoundsException(String.format("%d", this.taskList.taskLength()));
        }

        stringBuilder.append("    Noted. I've removed this task:\n");
        stringBuilder.append(this.taskList.remove(input));
        stringBuilder.append(String.format("\n    Now you have %d tasks in the list.", this.taskList.taskLength()));

        return stringBuilder.toString();
    }

    /**
     * Prints the tasks matching the specified key using the taskList's find method
     *
     * @param key The keyword or item to search for in the tasks.
     */
    public String findTaskUI(String key) {
        return this.taskList.find(key);
    }

    /**
     * Displays the list of tasks in the TaskList.
     */
    public String listItems() {
        return taskList.toString();
    }


    /**
     * Displays an error message.
     *
     * @param error The error message to be displayed.
     */
    public static String error(String error) {
        return error;
    }


}
