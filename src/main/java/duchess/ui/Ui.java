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
     * The opening greeting message when the program starts.
     */
    public String openingGreeting() {
        String logo = "___  _     _ ____  _     _  ____ ____ ____ \n" +
                " |     \\   |     |   |            |__|    |___  [__     [__  \n" +
                " |__/   |__|   |___    |     |    |___  ___]  ___] \n" +
                "                                   ";
        return String.format(logo + "\n" + "Hello! I'm Duchess.\n" + "What can I do for you today?\n");
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
     * @return The closing farewell message.
     */
    public String showClosingGreeting() {
        return "Farewell. Hope to see you again soon, my dear!";
    }

    /**
     * Displays a confirmation message after adding a task.
     *
     * @param task The task that was added.
     * @param taskCount The total number of tasks.
     * @param taskType The type of task added (e.g., todo, deadline, event).
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
     * @param task The task that was deleted.
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
     * Displays the list of matching tasks found after a search.
     *
     * @param matchingTasks The list of matching tasks found.
     * @return The list of matching tasks as a formatted String.
     */
    public String showFind(ArrayList<Pair<Integer, Task>> matchingTasks) {
        StringBuilder sb = new StringBuilder();

        if (!matchingTasks.isEmpty()) {
            sb.append("Here are the matching tasks in your list:\n");
            for (Pair<Integer, Task> pair : matchingTasks) {
                int originalIndex = pair.getKey() + 1; // Add 1 to match the original index
                Task task = pair.getValue();
                sb.append(" ").append(originalIndex).append(". ").append(task.toString()).append("\n");;
            }
        } else {
            sb.append("No matching tasks found.");
        }

        return sb.toString();
    }
}
