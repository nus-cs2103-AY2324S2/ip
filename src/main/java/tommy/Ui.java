package tommy;

import java.util.Scanner;

import tommy.task.Task;
import tommy.task.TaskList;

import tommy.exception.TommyException;

/**
 * Represents the class that deals with all displays and interactions with the user.
 */
public class Ui {

    private static final String NAME = "Tommy";
    private Scanner scanner;
//    private static final String DIVIDER = "____________________________";

    /**
     * Constructor for an Ui object that initialises the scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the greeting message for the user when the Chatbot boots up.
     *
     * @return Greeting messages for the user.
     */
    public String greet() {
        //Greetings

        return "\nHello! I'm " + this.NAME + "\nWhat can I do for you?\n";
    }

    /**
     * Prints the farewell message for the user when the Chatbot terminates.
     *
     * @return Farewell message to user.
     */
    public String farewell() {
        this.scanner.close();
        return "Bye. Hope to see you again soon!\n";
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
    public static String displayAllTasks(TaskList taskList) {
        StringBuilder acc = new StringBuilder("Here are the tasks in your list:\n");

        int length = taskList.getSize();

        for (int i = 0; i < length; i++) {
            Task task = taskList.getTaskAtPosition(i + 1);
            acc.append(i + 1 + "." + task.toString() + "\n");
        }

        return acc.toString();
    }

    /**
     * Prints the description of the task that is deleted.
     *
     * @param taskList List of tasks after deletion.
     * @param task Task that is deleted.
     */
    public static String displayDeletedTask(TaskList taskList, Task task) {
        String descOfTaskToDelete = task.toString();
        String descToDisplay = "Noted. I've removed this task:\n  " + descOfTaskToDelete +
                "\nNow you have " + taskList.getSize() + " tasks in the list.";

        return descToDisplay;
    }

    /**
     * Prints the description of the task that was marked.
     *
     * @param taskList List of tasks from which the task was marked.
     * @param position Position of the task that was marked in the taskList.
     */
    public static String displayMarkedTask(TaskList taskList, int position) {
        Task markedTask = taskList.getTaskAtPosition(position);

        String descToDisplay = "Nice! I've marked this task as done:\n" + "  " + markedTask.toString();

        return descToDisplay;
    }

    /**
     * Prints the description of the task that was unmarked.
     *
     * @param taskList List of tasks from which the task was unmarked.
     * @param position Position of the task that was unmarked in the taskList.
     */
    public static String displayUnmarkedTask(TaskList taskList, int position) {
        Task unmarkedTask = taskList.getTaskAtPosition(position);

        String descToDisplay = "OK, I've marked this task as not done yet:\n" +
                "  " + unmarkedTask.toString();

        return descToDisplay;
    }

    /**
     * Prints the description of the task that was newly created.
     *
     * @param task Newly created task.
     * @param taskList List of tasks from which the task was added to.
     */
    public static String displayNewTask(Task task, TaskList taskList) {
        String descToDisplay = "Got it. I've added this task:\n" +
                "  " + task.toString() + "\nNow you have " + taskList.getSize() + " tasks in the list.";

        return descToDisplay;
    }

    /**
     * Prints the list of tasks in the taskList that matches the keyword.
     *
     * @param taskList List of tasks to match the keyword with.
     * @param keyword Keyword to match the task description with.
     */
    public static String displayMatchingTasks(TaskList taskList, String keyword) {
        StringBuilder descToDisplay = new StringBuilder("Here are the matching tasks in your list:");
        int length = taskList.getSize();
        int counter = 0;


        for (int i = 1; i < length + 1; i++) {
            Task task = taskList.getTaskAtPosition(i);
            if (task.toString().contains(keyword)) {
                counter++;
                descToDisplay.append(counter + "." + task + "\n");
            }
        }

        return descToDisplay.toString();
    }

    /**
     * Prints the relevant error message for the TommyException thrown.
     */
    public static String printError(TommyException e) {
        return e.errorMessage() ;
    }

    /**
     * Prints a loading error message when loading from storage fails.
     */
    public static void showLoadingError() {
        System.out.println("There is an error loading the Storage");
    }
}
