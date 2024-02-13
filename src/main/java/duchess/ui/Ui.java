package duchess.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duchess.DuchessException;
import duchess.TaskList;
import duchess.storage.Storage;
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
     * Prints the opening greeting message when the program starts.
     */
    public void printOpeningGreeting() {
        String logo = " ____            __\n"
                + "|  _ \\ _   ______| |      ___  ___  ___\n"
                + "| | | | | | |  __| |__  /  _ \\/ __|/ __|\n"
                + "| |_| | |_| | |__| ___ |   __/\\__ \\\\__ \\\n"
                + "|____/ \\__,_|____|_| |_|\\ ___||___/|___/\n";
        printHorizontalLine();
        System.out.println(logo);
        printHorizontalLine();
        System.out.println("Hello! I'm Duchess.");
        System.out.println("What can I do for you today?");
        printHorizontalLine();
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

    public String showOpeningGreeting() {
        return "Hello, I'm Duchess. What can I do for you today?";
    }

    public String showClosingGreeting() {
        return "Farewell. Hope to see you again soon, my dear!";
    }

    public String showAdd(Task task, int taskCount, String taskType) {
        return String.format(
                "Understood. I've added this " + taskType + " task:\n"
                        + task.toString() + "\n"
                        + "Now you have " + taskCount + " tasks in the list." ) ;
    }

    public String showList(TaskList taskList) {
        return taskList.toString();
    }

    public String showDelete(Task task, int taskCount) {
        return String.format(
                "Understood. I've deleted this task:\n"
                        + task.toString() + "\n"
                        + "Now you have " + taskCount + " tasks in the list." ) ;
    }

    public String showMarked(Task task) {
        return String.format(
                "Perfect! I've marked this task as done:\n"
                        + task.toString() + "\n");
    }

    public String showUnmarked(Task task) {
        return String.format(
                "Understood, I've marked this task as not done yet:\n"
                        + task.toString() + "\n");
    }

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
