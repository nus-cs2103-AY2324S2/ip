package tommy;

import java.util.Scanner;

import tommy.task.Task;
import tommy.task.TaskList;

/**
 * Represents the class that deals with all displays and interactions with the user.
 */
public class Ui {

    private static final String NAME = "Tommy";
    private Scanner scanner;

    /**
     * Constructor for an Ui object that initialises the scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns the greeting message for the user when the Chatbot boots up.
     *
     * @return Greeting messages for the user.
     */
    public String greet() {
        return "Hello! I'm " + this.NAME + "\nWhat can I do for you?";
    }

    /**
     * Returns farewell message for the user when the Chatbot terminates.
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
     * Numbers the entire taskList and as a single String so all the tasks can be displayed in a list.
     *
     * @param taskList List of tasks to be numbered.
     * @returns Numbered list of tasks as a String.
     */
    public static String displayAllTasks(TaskList taskList) {
        int length = taskList.getSize();
        StringBuilder accumulator = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 1; i <= length; i++) {
            assert i > 0 && i < taskList.getSize() : "Index must be within size of taskList";

            String counter = i + ".";
            Task task = taskList.getTaskAtPosition(i);
            String descOfTask = task.toString();

            accumulator.append(counter + descOfTask + System.lineSeparator());
        }
        accumulator.append(System.lineSeparator());

        return accumulator.toString();
    }

    /**
     * Returns the confirmation description of the task that is deleted.
     *
     * @param taskList List of tasks after deletion.
     * @param task Task that is deleted.
     * @return Confirmation description of the deleted task.
     */
    public static String displayDeletedTask(TaskList taskList, Task task) {
        String descOfTaskToDelete = task.toString();
        String deletedTaskConfirmation = "Noted. I've removed this task:\n  ";
        String taskListSummary = "\nNow you have " + taskList.getSize() + " tasks in the list.";

        String descToDisplay = deletedTaskConfirmation + descOfTaskToDelete + taskListSummary;

        return descToDisplay;
    }

    /**
     * Returns the description of the task that was marked.
     *
     * @param taskList List of tasks from which the task was marked.
     * @param position Position of the task that was marked in the taskList.
     * @return Description of the marked task.
     */
    public static String displayMarkedTask(TaskList taskList, int position) {
        assert position <= taskList.getSize() : "Position of task must be within size of taskList";

        Task markedTask = taskList.getTaskAtPosition(position);
        String descOfMarkedTask = markedTask.toString();
        String markedTaskConfirmation = "Nice! I've marked this task as done:\n" + "  ";

        String descToDisplay = markedTaskConfirmation + descOfMarkedTask;

        return descToDisplay;
    }

    /**
     * Returns the description of the task that was unmarked.
     *
     * @param taskList List of tasks from which the task was unmarked.
     * @param position Position of the task that was unmarked in the taskList.
     * @return Description of the unmarked task.
     */
    public static String displayUnmarkedTask(TaskList taskList, int position) {
        assert position <= taskList.getSize() : "Position of task must be within size of taskList";

        String unmarkedTaskConfirmation = "OK, I've marked this task as not done yet:\n" + "  ";
        Task unmarkedTask = taskList.getTaskAtPosition(position);
        String descOfUnmarkedTask = unmarkedTask.toString();

        String descToDisplay = unmarkedTaskConfirmation + descOfUnmarkedTask;

        return descToDisplay;
    }

    /**
     * Returns the description of the task that was newly created.
     *
     * @param task Newly created task.
     * @param taskList List of tasks from which the task was added to.
     * @return Description of the newly created task.
     */
    public static String displayNewTask(Task task, TaskList taskList) {
        String newTaskConfirmation = "Got it. I've added this task:\n" + "  ";
        String descOfNewTask = task.toString();
        String taskListSummary = "\nNow you have " + taskList.getSize() + " tasks in the list.";

        String descToDisplay = newTaskConfirmation + descOfNewTask + taskListSummary;

        return descToDisplay;
    }

    /**
     * Returns the list of tasks in the taskList that matches the keyword.
     *
     * @param taskList List of tasks to match the keyword with.
     * @param keyword Keyword to match the task description with.
     * @return All the tasks with matched description.
     */
    public static String displayMatchingTasks(TaskList taskList, String keyword) {
        StringBuilder accumulator = new StringBuilder("Here are the matching tasks in your list:\n");
        int length = taskList.getSize();
        int counter = 1;

        for (int i = 1; i <= length; i++) {
            assert i > 0 && i <= taskList.getSize() : "Position must be within size of taskList";

            Task task = taskList.getTaskAtPosition(i);
            Boolean containsKeyword = task.toString().contains(keyword);

            if (containsKeyword) {
                String position = counter + ".";
                accumulator.append(position + task + System.lineSeparator());
                counter++;
                assert counter <= taskList.getSize() : "Number of matched tasks must be a subset of taskList";
            }
        }
        accumulator.append(System.lineSeparator());

        return accumulator.toString();
    }

    /**
     * Prints a loading error message when loading from storage fails.
     */
    public static void showLoadingError() {
        System.out.println("There is an error loading the Storage");
    }
}
