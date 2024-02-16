package duke;

import duke.task.Task;
import java.util.Scanner;

/**
 * Encapsulates the way the user can interact with the chatbot.
 */
public class Ui {
    /**
     * Reads the command that the user type.
     *
     * @return The input from the user.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Blob.");
        System.out.println("What can I do for you?\n");
    }

    /**
     * Prints an error message that tells that the user types in the wrong format.
     */
    public void showWrongFormat() {
        System.err.println("Wrong format! :(");
    }

    /**
     * Prints out all the Tasks that the user has.
     *
     * @param tasks The user's TaskList
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks);
        System.out.println();
    }

    /**
     * Prints an error message that tells that the user forgets to type the task number.
     */
    public void showForgetTaskNumber() {
        System.err.println("You forgot to type which task!");
    }

    /**
     * Prints the correct format for setting a task as Done.
     */
    public void showMarkFormat() {
        System.out.println("Type: 'mark n' to mark the n-th task.");
        System.out.println("For example type: 'mark 1' to mark the first task.\n");
    }

    /**
     * Prints the correct format for setting a task as Undone.
     */
    public void showUnmarkFormat() {
        System.out.println("Type: 'unmark n' to unmark the n-th task.");
        System.out.println("For example, type: 'unmark 1' to mark the first task.\n");
    }

    /**
     * Prints the correct format for deleting a task.
     */
    public void showDeleteFormat() {
        System.out.println("Type: 'delete n' to delete the n-th task.");
        System.out.println("For example type: 'delete 1' to delete the first task.\n");
    }

    /**
     * Prints the correct format for typing in a date.
     */
    public void showDateFormat() {
        System.out.println("The correct date format is: YYYY-MM-DD\n");
    }

    /**
     * Prints the correct format for creating a ToDo task.
     */
    public void showToDoFormat() {
        System.out.println("The correct format is:");
        System.out.println("todo <description>\n");
    }

    /**
     * Prints the correct format for creating a Deadline Task.
     */
    public void showDeadlineFormat() {
        System.out.println("The correct format is:");
        System.out.println("deadline <description> /by <deadline time>\n");
    }

    /**
<<<<<<< HEAD
     * Prints the correct format for creating an Event task.
     */
=======
     * Prints the correct format of find command.
     */
    public void showFindFormat() {
        System.out.println("The correct format is:");
        System.out.println("find <keyWord>\n");
    }

>>>>>>> branch-Level-9
    public void showEventFormat() {
        System.out.println("The correct format is:");
        System.out.println("event <description> /from <start time> /to <end time>\n");
    }

    /**
     * Shows all the valid commands to the user.
     */
    public void showValidCommands() {
        System.out.println("You need to use 'todo', 'deadline' or 'event' command to add a task.");
        System.out.println("You can use 'list' to see all of your tasks.");
        System.out.println("Use 'mark' or 'unmark' for any of your tasks.\n");
    }

<<<<<<< HEAD
    /**
     * Tells the user that a task has been successfully added.
     *
     * @param task The task that has been added to the list.
     * @param taskListSize The size of the list after the task has been added.
     */
=======
    public void showMatchingTasks(String matchingTasks) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(matchingTasks);
    }

>>>>>>> branch-Level-9
    public void showCreateTask(Task task, int taskListSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskListSize + " tasks in the list.\n");
    }

    /**
     * Tells the user that the task number they inputted does not exist.
     */
    public void showNoTaskFound() {
        System.out.println("You don't have that task!\n");
    }

    /**
     * Tells the user that a task has been successfully set as Done.
     *
     * @param task The task that has been set as Done.
     */
    public void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task done:");
        System.out.println(task + "\n");
    }

    /**
     * Tells the user that a task has been successfully set as Undone.
     *
     * @param task The task that has been set as Undone.
     */
    public void showUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task + "\n");
    }

    /**
     * Tells the user that a task has been successfully deleted.
     *
     * @param task The task that has been deleted.
     * @param taskListSize The size of the list after the task has been deleted.
     */
    public void showDeleteTask(Task task, int taskListSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskListSize + " tasks in the list.\n");
    }

    /**
     * Prints the exit message to the user.
     */
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
