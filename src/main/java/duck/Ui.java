package duck;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The Ui class handles user interface-related operations, such as displaying messages.
 */
public class Ui {
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Gets user input from the console.
     *
     * @return User input as a string.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println(" Hello! I'm Duck");
        System.out.println(" What can I do for you?");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbyeMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(" " + errorMessage);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(List<Task> tasks) {
        System.out.println(LINE_SEPARATOR);
        if (tasks.isEmpty()) {
            System.out.println(" There are no tasks in your list.");
        } else {
            System.out.println(" Here" + (tasks.size() == 1 ? " is the " : " are the ") + tasks.size()
                    + (tasks.size() == 1 ? " task " : " tasks ") + "in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a confirmation message after adding a task.
     *
     * @param tasks The updated list of tasks.
     */
    public void showConfirmationMessage(List<Task> tasks) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.get(tasks.size() - 1));
        System.out.println(" Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list.");
        System.out.println(LINE_SEPARATOR);

    }

    /**
     * Displays a message after deleting a task.
     *
     * @param tasks        The updated list of tasks.
     * @param removedTask The task that was removed.
     */
    public void showDeleteMessage(List<Task> tasks, Task removedTask) {
        Task removedTaskk = removedTask;
        System.out.println(LINE_SEPARATOR);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removedTaskk);
        System.out.println(" Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list.");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays an error message for an invalid task index.
     */
    public void invalidTaskIndex() {
        System.out.println(LINE_SEPARATOR);
        System.out.println(" Invalid task index. Please enter a valid task index.");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a message after marking a task as done.
     *
     * @param markedTask The task that was marked as done.
     */
    public static void showMarkTaskAsDone(Task markedTask) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + markedTask);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a message after marking a task as not done.
     *
     * @param markedTask The task that was marked as not done.
     */
    public static void showUnmarkTaskAsDone(Task markedTask) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + markedTask);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays tasks matching a keyword.
     *
     * @param keyword The keyword to search for.
     * @param tasks   The list of tasks to search in.
     */
    public static void findTasksByKeyword(String keyword, List<Task> tasks) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(" Here are the matching tasks in your list:");

        // Filter tasks based on the keyword
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());

        // Display the matching tasks according to keyword
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + matchingTasks.get(i));
        }

        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Generates a message for displaying tasks found by a keyword search.
     *
     * @param keyword    The keyword used in the search.
     * @param foundTasks The list of tasks found by the search.
     * @return A string message displaying the found tasks.
     */
    public String getFindTasksMessage(String keyword, List<Task> foundTasks) {
        StringBuilder message = new StringBuilder("Tasks found with keyword '" + keyword + "':\n");

        if (foundTasks.isEmpty()) {
            message.append("No matching tasks found.\n");
        } else {
            for (int i = 0; i < foundTasks.size(); i++) {
                message.append((i + 1) + ". " + foundTasks.get(i) + "\n");
            }
        }

        return message.toString();
    }

    /**
     * Generates a message for displaying the list of tasks.
     *
     * @param taskList The list of tasks to be displayed.
     * @return A string message displaying the list of tasks.
     */
    public String getTaskListMessage(List<Task> taskList) {
        StringBuilder message = new StringBuilder("Here are your tasks:\n");

        if (taskList.isEmpty()) {
            message.append("No tasks in the list.\n");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                message.append((i + 1) + ". " + taskList.get(i) + "\n");
            }
        }

        return message.toString();
    }

    /**
     * Displays a DukeException message.
     *
     * @param e The DukeException.
     */
    public void showDukeExceptionMessage(DukeException e) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(" " + e.getMessage());
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a DukeDataCorruptedException message.
     *
     * @param e The DukeDataCorruptedException.
     */
    public void showDukeDataCorruptionMessage(DukeDataCorruptedException e) {
        System.out.println("Error: The data file is corrupted. Please check the file format.");
    }

    /**
     * Displays an IOException message.
     */
    public void showIoExceptionMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Error saving or loading tasks. Please check the file.");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a NumberFormatException message.
     */
    public void showNumberFormatExceptionMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Please enter a valid task index.");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a message for a FileNotFoundException.
     */
    public void showFileNotFoundExceptionMessage() {
        System.out.println("Data file not found. Creating a new one.");
    }
}


