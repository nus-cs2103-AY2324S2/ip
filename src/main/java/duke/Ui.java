package duke;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

import duke.task.Task;
import duke.task.TaskList;

import duke.exception.DukeException;

/**
 * Represents the class that deals with all displays and interactions with the user.
 */
public class Ui {

    private static final String name = "Tommy";
    private Scanner scanner;

    /**
     * Constructor for an Ui object that initialises the scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the greeting message for the user when the Chatbot boots up.
     */
    public void greet() {
        //Greetings
        printDivider();
        System.out.println("Hello! I'm " + this.name);
        System.out.println("What can I do for you?");
        printDivider();
    }

    /**
     * Prints the farewell message for the user when the Chatbot terminates.
     */
    public void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
        this.scanner.close();
    }

    /**
     * Extracts the input from the user and returns it as a String.
     *
     * @return String of the input by user.
     */
    public String getInput() {
        String userInput = this.scanner.nextLine();
        return userInput;
    }

    /**
     * Prints the entire list of tasks in the taskList numbered.
     *
     * @param taskList List of tasks to be printed.
     */
    public static void displayAllTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");

        int length = taskList.getSize();

        for (int i = 0; i < length; i++) {
            Task task = taskList.getTaskAtPosition(i + 1);
            System.out.println(i + 1 + "." + task.toString());
        }

        printDivider();
    }

    /**
     * Prints the description of the task that is deleted.
     *
     * @param taskList List of tasks after deletion.
     * @param task Task that is deleted.
     */
    public static void displayDeletedTask(TaskList taskList, Task task) {
        String descOfTaskToDelete = task.toString();

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + descOfTaskToDelete);
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");

        printDivider();
    }

    /**
     * Prints the description of the task that was marked.
     *
     * @param taskList List of tasks from which the task was marked.
     * @param position Position of the task that was marked in the taskList.
     */
    public static void displayMarkedTask(TaskList taskList, int position) {
        Task markedTask = taskList.getTaskAtPosition(position);

        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + markedTask.toString());

        printDivider();
    }

    /**
     * Prints the description of the task that was unmarked.
     *
     * @param taskList List of tasks from which the task was unmarked.
     * @param position Position of the task that was unmarked in the taskList.
     */
    public static void displayUnmarkedTask(TaskList taskList, int position) {
        Task unmarkedTask = taskList.getTaskAtPosition(position);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + unmarkedTask.toString());

        printDivider();
    }

    /**
     * Prints the description of the task that was newly created.
     *
     * @param task Newly created task.
     * @param taskList List of tasks from which the task was added to.
     */
    public static void displayNewTask(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");

        printDivider();

    }

    /**
     * Prints the list of tasks in the taskList that matches the keyword.
     *
     * @param taskList List of tasks to match the keyword with.
     * @param keyword Keyword to match the task description with.
     */
    public static void displayMatchingTasks(TaskList taskList, String keyword) {
        System.out.println("Here are the matching tasks in your list:");

        int length = taskList.getSize();
        int counter = 0;

        for (int i = 1; i < length + 1; i++) {
            Task task = taskList.getTaskAtPosition(i);
            if (task.toString().contains(keyword)) {
                counter++;
                System.out.println(counter + "." + task);
            }
        }

        printDivider();
    }

    /**
     * Prints the relevant error message for the DukeException thrown.
     */
    public static void printError(DukeException e) {
        System.out.println(e.errorMessage());
        printDivider();
    }

    /**
     * Prints a loading error message when loading from storage fails.
     */
    public static void showLoadingError() {
        System.out.println("There is an error loading the Storage");
    }

    /**
     * Prints a divider that separates different segments for aesthetic purposes.
     */
    private static void printDivider() {
        System.out.println("____________________________");
    }
}
