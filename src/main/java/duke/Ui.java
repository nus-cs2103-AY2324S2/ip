package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * The `Ui` class represents an Ui interface.
 * It provides methods to print text responses to users.
 */
public class Ui {

    private static final String LONG_LINE = "____________________________________________________________";

    /**
     * Prints loading error messages.
     */
    public void showLoadingError() {
        System.out.println(Ui.LONG_LINE);
        System.out.println("No saved tasks.");
        System.out.println(Ui.LONG_LINE);
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        // Print logo
        String logo = " __   ___  _____   __       __       __    __      ___\n"
                + "|  | /  / |_   _| |  |     |  |     |  |  |  |    / _ \\\n"
                + "|  |/  /    | |   |  |     |  |     |  |  |  |   / /_\\ \\\n"
                + "|  |\\  \\   _| |_  |  |___  |  |___  |  |__|  |  / _____ \\\n"
                + "|__| \\__\\ |_____| |______| |______|  \\______/  /_/     \\_\\\n";
        System.out.println(logo);

        // Greet
        System.out.println(Ui.LONG_LINE);
        System.out.println("Killua online. What's my next quest?");
        System.out.println(Ui.LONG_LINE);
    }

    /**
     * Returns a user input.
     *
     * @return User input as string.
     */
    public String readCommand() {
        Scanner inputReader = new Scanner(System.in);
        return inputReader.nextLine();
    }

    /**
     * Prints a divider.
     */
    public void showLine() {
        System.out.println(Ui.LONG_LINE);
    }

    /**
     * Prints an error message.
     *
     * @param message Error message to be printed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints all tasks in the given task list.
     *
     * @param tasks Task list containing tasks.
     */
    public void list(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getItems().size(); i++) {
            Task nextTask = tasks.getItems().get(i);
            System.out.println((i + 1) + ". " + nextTask.getDescriptionStatus());
        }
    }

    /**
     * Prints all tasks that contains the keyword in the given task list.
     *
     * @param tasks Task list containing tasks.
     * @param keyword A string to be contained in task.
     */
    public void list(TaskList tasks, String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0, j = 0; i < tasks.getItems().size(); i++) {
            Task nextTask = tasks.getItems().get(i);
            if (nextTask.getDescriptionStatus().contains(keyword)) {
                System.out.println((j + 1) + ". " + nextTask.getDescriptionStatus());
                j++;
            }
        }
    }

    /**
     * Prints a feedback on the change of task status.
     *
     * @param task Task that have changed status.
     */
    public void mark(Task task) {
        System.out.println(task.getMarkStatus());
        System.out.println(task.getDescriptionStatus());
    }

    /**
     * Prints a feedback on the add of task.
     *
     * @param task Task to be added.
     */
    public void add(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.getDescriptionStatus());
        System.out.println("Now you have " + tasks.getItems().size() + " tasks in the list.");
    }

    /**
     * Prints a feedback on the deletion of task.
     *
     * @param task Task to be deleted.
     */
    public void delete(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.getDescriptionStatus());
        System.out.println("Now you have " + (tasks.getItems().size() - 1) + " tasks in the list.");
    }

    /**
     * Prints program exit message.
     */
    public void exit() {
        // Exit
        System.out.println("Alright, I'm always one call away.");
    }


}
