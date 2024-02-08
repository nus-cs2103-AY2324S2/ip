package liv.ui;

import java.util.Scanner;
import liv.container.TaskList;
import liv.task.Task;
import liv.task.TodoTask;
import liv.task.Deadline;
import liv.task.Event;

/**
 * Handles the interaction between users and the codes.
 */
public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Displays the long bar between messages.
     */
    public void displayBar() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the greeting message.
     */
    public void displayGreetCommand() {
        System.out.println("Liv, under your instructions!");
        System.out.println("What is your command?\n");
    }

    /**
     * Reads the input command from the users.
     * @return The line that user types.
     */
    public String readCommand() {
        return SCANNER.nextLine();
    }

    /**
     * Prints the goodbye message.
     */
    public static void displayByeCommand() {
        System.out.println("Farewell, see you next time!");
    }

    /**
     * Prints the list of tasks to the user.
     */
    public static void displayListCommand() {
        System.out.println("Here are the missions you added:");
        for (int i = 0; i < TaskList.getListSize(); i++) {
            Task task = TaskList.getTask(i);
            int displayedIndex = i + 1;
            System.out.println(displayedIndex + ". " + task.getDisplayedString());
            //System.out.printf("%d. %s %s\n", displayedIndex, task.getStatusIcon(), task.getDescription());
        }
        System.out.println("Total: " + TaskList.getListSize() + " mission(s)");
    }

    /**
     * Prints the task that was marked by the command from the user.
     * @param task The task that was marked.
     */
    public static void displayMarkCommand(Task task) {
        System.out.println("Mission completed:\n");
        System.out.println(" " + task.getStatusIcon() + " " + task.getDescription());
    }

    /**
     * Prints the task that was unmarked by the command from the user.
     * @param task The task that was unmarked.
     */
    public static void displayUnmarkCommand(Task task) {
        System.out.println("Mission pending:\n");
        System.out.println(" " + task.getStatusIcon() + " " + task.getDescription());
    }

    /**
     * Prints the task that was deleted from the list by the user.
     * @param removed The task removed from the list.
     */
    public static void displayDeleteCommand(Task removed) {
        System.out.println("Mission deleted from list:");
        System.out.println(removed.getDisplayedString());
        System.out.println("You have " + TaskList.getListSize() + " mission(s) on the list");
    }

    /**
     * Prints the task that was added by the user in the predefined format.
     * @param todo The task that user added to the list.
     */
    public static void displayTodoCommand(TodoTask todo) {
        System.out.println("Task added:");
        System.out.println(todo.getDisplayedString());
        System.out.println("You have " + TaskList.getListSize() + " mission(s) on the list");
    }

    /**
     * Prints the deadline that was added by the user in the predefined format.
     * @param deadline The deadline that user added to the list.
     */
    public static void displayDeadlineCommand(Deadline deadline) {
        System.out.println("Deadline added:");
        System.out.println(deadline.getDisplayedString());
        System.out.println("You have " + TaskList.getListSize() + " mission(s) on the list");
    }

    /**
     * Prints the deadline that was added by the user in the predefined format.
     * @param event The event that user added to the list.
     */
    public static void displayEventCommand(Event event) {
        System.out.println("Event added:");
        System.out.println(event.getDisplayedString());
        System.out.println("You have " + TaskList.getListSize() + " mission(s) on the list");
    }

    /**
     * Prints the error message that was thrown by the Exception class.
     * @param message The exception message thrown.
     */
    public void displayErrorMessage(String message) {
        System.out.println(message);
    }
}
