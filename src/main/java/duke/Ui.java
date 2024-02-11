package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    // Read input from the user
    public String readCommand() {
        //System.out.print("Enter command: "); // Prompt for user input
        return scanner.nextLine();
    }

    // Show welcome message
    public void showWelcome() {
        System.out.println("Hello! I'm BBJSOBB");
        System.out.println("What can I do for you?");
    }

    // Show error messages
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    // Show loading error
    public void showLoadingError() {
        showError("Could not load tasks from file.");
    }

    // Show tasks to the user
    public void showTask(String task) {
        System.out.println(task);
    }

    // Show add task message
    public void showAddedTask(String task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    // Show task removal message
    public void showRemovedTask(String task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    // Show the list of tasks
    public void showTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
    }

    // Show exit message
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    // Close the scanner when the UI is closing
    public void close() {
        scanner.close();
    }
}

