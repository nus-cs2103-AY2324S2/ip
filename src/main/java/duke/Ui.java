package duke;

import duke.Tasks.Task;
import duke.Tasks.TaskList;

import java.util.Scanner;
import java.util.List;


import java.io.InputStream;


/**
 * Handles user interaction, including input and output.
 */
public class Ui {
    private Scanner scanner;


    /**
     * Constructs a Ui object with a Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String greet() {
        return "Hello! I'm BotYue. \nWhat can I do for you?";
    }


    /**
     * Reads a command entered by the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a welcome message when the program starts.
     */
    public void showWelcome() {
        System.out.println("   ____________________________________________________________");
        System.out.println("    Hello! I'm BotYue");
        System.out.println("    What can I do for you?");
        System.out.println("   ____________________________________________________________");
    }


    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("   ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
        }

        System.out.println("   ____________________________________________________________");
    }


    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkedMessage(Task task) {
        System.out.println("   ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
        System.out.println("   ____________________________________________________________");
    }


    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showUnmarkedMessage(Task task) {
        System.out.println("   ____________________________________________________________");
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + task);
        System.out.println("   ____________________________________________________________");
    }


    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task  The task that has been deleted.
     * @param count The updated number of tasks in the list.
     */
    public static void showDeletedMessage(Task task, int count) {
        System.out.println("   ____________________________________________________________");
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);

        System.out.println("    Now you have " + count + " tasks in the list.");
        System.out.println("   ____________________________________________________________");
    }

    /**
     * Displays a goodbye message when the user exits the program.
     */
    public static void showGoodbyeMessage() {
        System.out.println("   ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("   ____________________________________________________________");
    }


    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task  The task that has been added.
     * @param count The updated number of tasks in the list.
     */
    public void showAddedMessage(Task task, int count) {
        System.out.println("   ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + count + " tasks in the list.");
        System.out.println("   ____________________________________________________________");
    }

    /**
     * Displays the tasks that match the given keyword in the task list.
     *
     * @param matchingTasks The task list containing tasks to search within.
     */
    public void showMatchingTasks(List<Task> matchingTasks) {
        System.out.println("   ____________________________________________________________");
        System.out.println("    Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + matchingTasks.get(i));
        }
        System.out.println("   ____________________________________________________________");
    }

    /**
     * Displays an error message when tasks fail to load from file.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }
}

    /*public String showLoadingError(DukeException e) {
        String message = "Error loading tasks from file.";
        return message;
    }


}
*/
