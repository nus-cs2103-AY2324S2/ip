package haro;

import java.util.Scanner;

import haro.task.Task;


/**
 * The Ui class handles user interface interactions, reading user input and displaying messages.
 * It provides methods for greeting, saying bye, reading user command inputs,
 * showing errors and printing out task information.
 */
public class Ui {
    private static final String haroLogo = " ___  ___  ________  ________  ________\n"
            + "|\\  \\|\\  \\|\\   __  \\|\\   __  \\|\\   __  \\\n"
            + "\\ \\  \\\\\\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\\n"
            + " \\ \\   __  \\ \\   __  \\ \\   _  _\\ \\  \\\\\\  \\\n"
            + "  \\ \\  \\ \\  \\ \\  \\ \\  \\ \\  \\\\  \\\\ \\  \\\\\\  \\\n"
            + "   \\ \\__\\ \\__\\ \\__\\ \\__\\ \\__\\\\ _\\\\ \\_______\\\n"
            + "    \\|__|\\|__|\\|__|\\|__|\\|__|\\|__|\\|_______|\n";
    private static final String horizontalLine = "______________________________________________";
    private static String openingMsg = "Heya! I'm Haro!\n" + "What can I do for you today?";
    private static String closingMSg = "Bye. Hope to see you some time soon!";
    private Scanner inputScanner;

    /**
     * Constructs a Ui instance with a scanner for user input.
     */
    public Ui() {
        this.inputScanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message with a Haro logo.
     */
    public static String greet() {
        return openingMsg;
    }

    /**
     * Displays a farewell message and that the task list has been saved.
     */
    public String bye() {
        return closingMSg;
    }

    /**
     * Reads a command from the user.
     *
     * @return User input as a string
     */
    public String readCommand() {
        return inputScanner.nextLine();
    }

    /**
     * Displays an error message.
     *
     * @param errorMsg Error message to be displayed
     */
    public String showError(String errorMsg) {
        return errorMsg;
    }

    /**
     * Prints all the Tasks within a TaskList.
     *
     * @param tasks TaskList containing tasks to be printed
     */
    public String printList(TaskList tasks) {
        String taskString = tasks.tasksToString();
        if (taskString == "") {
            return "The task list is currently empty! Add tasks!\n";
        } else {
            return "Here are the tasks in your list:\n" + taskString;
        }
    }

    /**
     * Prints a message for marking a task as done.
     * @param task Task that has been marked
     */
    public String printMarkTask(Task task) {
        return "Nice! I've marked this task as done\n" + task.printTask();
    }

    /**
     * Prints a message for unMarking a task.
     *
     * @param task Task that has been unmarked
     */
    public String printUnmarkTask(Task task) {
        return "Alright, I've marked this task as not done yet\n"
                + task.printTask();
    }

    /**
     * Prints a message for adding a task.
     *
     * @param task Task that has been added
     * @param taskListSize Current size of the task list after addition of task
     */
    public String printAddTask(Task task, int taskListSize) {
        return "Got it I've added this task:\n"
                + task.printTask() + "\n"
                + "You now have " + taskListSize + " tasks in the list\n";
    }

    /**
     * Prints a message for deleting a task.
     *
     * @param task          Task that has been deleted
     * @param taskListSize  Current size of the task list after deletion of task
     */
    public String printDeleteTask(Task task, int taskListSize) {
        return "Noted. I've removed this task\n" + task.printTask() + "\n"
                + "You now have " + taskListSize + " tasks in the list\n";
    }

    /**
     * Prints the result of a search operation.
     *
     * @param taskString String containing the matching tasks
     */
    public String printSearch(String taskString) {
        if (taskString.equals("")) {
            return "Sorry there are no current matches in your list! :(\n";
        } else {
            return "Here are the matching tasks in your list:\n" + taskString;
        }
    }

    /**
     * Prints the result of a task edit operation.
     *
     * @param task Task objected of the edited task
     * @param index Index of the task in the task list
     * @return A message telling the user the task has been updated
     */
    public String printEditTask(Task task, int index) {
        int taskNumber = index + 1;
        return "Got it I've edited this task:\n"
                + taskNumber + ". " + task.printTask();
    }
}
