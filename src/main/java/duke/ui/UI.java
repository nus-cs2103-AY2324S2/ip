package duke.ui;

import duke.storage.TaskList;
import duke.storage.Task;
import duke.storage.Storage;

import duke.dukeException.*;

/**
 * The UI class handles interactions with the user through the command-line interface.
 * It displays greetings, goodbyes, and processes user input to perform various tasks.
 */
public class UI {
    private static final String lines = "    ____________________________________________________________";
    private static final String name = "Wang";

    private final TaskList taskList;

    /**
     * Constructs a UI object and initializes the associated TaskList.
     */
    public UI() {
        this.taskList = new TaskList();
        Storage.start(taskList);
    }

    /**
     * Displays a greeting message when the Duke program starts.
     */
    public static void greeting() {
        System.out.printf("    Hello! I'm %s\n", name);
        System.out.println("    What can I do for you?");
        System.out.println(lines);
    }

    /**
     * Displays a goodbye message when the Duke program ends.
     */
    public static void goodbye() {
        System.out.println(lines);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(lines);
    }


    /**
     * Adds a task to the TaskList and displays a confirmation message.
     *
     * @param task The task to be added.
     */
    public void addItem(Task task) {
        this.taskList.add(task);
        System.out.println(lines);
        System.out.println("    " + "Got it. I've added this task:\n" + "      " + task + "\n" + "" +
                String.format("    Now you have %d tasks in the list.", this.taskList.taskLength()));
        System.out.println(lines);
    }

    /**
     * Marks a task as done and displays a confirmation message.
     *
     * @param input The index of the task to be marked as done.
     * @throws ListOutofBoundsException If the provided index is out of bounds.
     */
    public void markTaskUI(int input) throws ListOutofBoundsException{
        if (input <0 || input > this.taskList.taskLength() - 1) {
            throw new ListOutofBoundsException(String.format("%d", this.taskList.taskLength()));
        }
        System.out.println(lines);
        System.out.println("    Nice! I've marked this task as done:");
        this.taskList.markTask(input);
        System.out.println(lines);
    }

    /**
     * Marks a task as not done and displays a confirmation message.
     *
     * @param input The index of the task to be marked as not done.
     * @throws ListOutofBoundsException If the provided index is out of bounds.
     */
    public void unMarkTask(int input) throws ListOutofBoundsException{
        if (input < 0 || input > this.taskList.taskLength() - 1) {
            throw new ListOutofBoundsException(String.format("%d", this.taskList.taskLength()));
        }
        System.out.println(lines);
        System.out.println("    OK, I've marked this task as not done yet:");
        this.taskList.unMarkTask(input);
        System.out.println(lines);
    }

    /**
     * Removes a task from the TaskList and displays a confirmation message.
     *
     * @param input The index of the task to be removed.
     * @throws ListOutofBoundsException If the provided index is out of bounds.
     */
    public void removeTask(int input) throws ListOutofBoundsException {

        if (input < 0 || input > this.taskList.taskLength() - 1) {
            throw new ListOutofBoundsException(String.format("%d", this.taskList.taskLength()));
        }
        System.out.println(lines);
        System.out.println("    Noted. I've removed this task:");
        this.taskList.remove(input);
        System.out.println(String.format("    Now you have %d tasks in the list.",this.taskList.taskLength()));
        System.out.println(lines);
    }

    /**
     * Displays the list of tasks in the TaskList.
     */
    public void listItems() {
        System.out.println(taskList);
    }


    /**
     * Displays an error message.
     *
     * @param error The error message to be displayed.
     */
    public static void error(String error) {
        System.out.println(lines);
        System.out.println(error);
        System.out.println(lines);
    }


}
