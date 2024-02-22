package duchess.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duchess.TaskList;
import duchess.task.Task;

import javafx.util.Pair;

/**
 * Ui class handles user interface interactions in the Duchess program.
 * It provides methods for printing greetings, messages, and reading user input.
 */
public class Ui {
    private static Scanner scanner = new Scanner(System.in);

    private static final String OPENING_GREETING = "___  _     _ ____  _     _  ____ ____ ____ \n"
            + " |     \\   |     |   |            |__|    |___  [__     [__  \n"
            + " |__/   |__|   |___    |     |    |___  ___]  ___] \n"
            + "                                   "
            + "\n" + "Hello! I'm Duchess.\n" + "What can I do for you today?\n";
    private static final String CLOSING_GREETING = "Farewell. Hope to see you again soon, my dear!";

    public String nextLine() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner used for reading user input.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Represents the opening greeting message displayed when the Duchess program starts.
     */
    public String openingGreeting() {
        return OPENING_GREETING;
    }


    /**
     * Prints the closing farewell message when the program ends.
     */
    public void printClosingGreeting() {
        printHorizontalLine();
        System.out.println("Farewell. Hope to see you again soon, my dear!");
        printHorizontalLine();
    }

    /**
     * Prints a horizontal line of dashes as a visual separator.
     */
    public void printHorizontalLine() {
        int lineLength = 50; // Specify the length of the line

        // Print the horizontal line
        for (int i = 0; i < lineLength; i++) {
            System.out.print("_");
        }

        System.out.println();
    }

    /**
     * Displays the closing farewell message.
     *
     * @return The closing farewell message.
     */
    public String showClosingGreeting() {
        return CLOSING_GREETING;
    }

    /**
     * Displays a confirmation message after adding a task.
     *
     * @param task      The task that was added.
     * @param taskCount The total number of tasks.
     * @param taskType  The type of task added (e.g., todo, deadline, event).
     * @return The confirmation message.
     */
    public String showAdd(Task task, int taskCount, String taskType) {
        return String.format(
                "Understood. I've added this " + taskType + " task:\n"
                        + task.toString() + "\n"
                        + "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList The list of tasks to display.
     * @return The list of tasks as a formatted String.
     */
    public String showList(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * Displays a confirmation message after deleting a task.
     *
     * @param task      The task that was deleted.
     * @param taskCount The total number of tasks after deletion.
     * @return The confirmation message.
     */
    public String showDelete(Task task, int taskCount) {
        return String.format(
                "Understood. I've deleted this task:\n"
                        + task.toString() + "\n"
                        + "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a confirmation message after marking a task as done.
     *
     * @param task The task that was marked as done.
     * @return The confirmation message.
     */
    public String showMarked(Task task) {
        return String.format(
                "Perfect! I've marked this task as done:\n"
                        + task.toString() + "\n");
    }

    /**
     * Displays a confirmation message after marking a task as not done yet.
     *
     * @param task The task that was marked as not done yet.
     * @return The confirmation message.
     */
    public String showUnmarked(Task task) {
        return String.format(
                "Understood, I've marked this task as not done yet:\n"
                        + task.toString() + "\n");
    }


    /**
     * Finds and returns a list of duplicate tasks in the task list.
     * A duplicate task is defined as a task with the same content as another task in the list.
     *
     * @return an ArrayList of Pair objects containing the index of the duplicate task in the original list and the
     *         duplicate task itself
     */
    public String showSubList(ArrayList<Pair<Integer, Task>> subListTasks, String subListType) {
        StringBuilder sb = new StringBuilder();

        if (!subListTasks.isEmpty()) {
            sb.append("Here are the ").append(subListType).append(" tasks in your list:\n");
            for (Pair<Integer, Task> pair : subListTasks) {
                int originalIndex = pair.getKey() + 1; // Add 1 to match the original index
                Task task = pair.getValue();
                sb.append(" ").append(originalIndex).append(". ").append(task.toString()).append("\n");
                ;
            }
        } else {
            sb.append("No ").append(subListType).append(" tasks found.");
        }

        return sb.toString();
    }

}
