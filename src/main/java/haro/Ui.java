package haro;

import haro.task.Task;

import java.util.Scanner;

/**
 * The Ui class handles user interface interactions, reading user input and displaying messages.
 * It provides methods for greeting, saying bye, reading user command inputs,
 * showing errors and printing out task information.
 */
public class Ui {
    private Scanner inputScanner;
    private final String haroLogo = " ___  ___  ________  ________  ________\n"
            + "|\\  \\|\\  \\|\\   __  \\|\\   __  \\|\\   __  \\\n"
            + "\\ \\  \\\\\\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\\n"
            + " \\ \\   __  \\ \\   __  \\ \\   _  _\\ \\  \\\\\\  \\\n"
            + "  \\ \\  \\ \\  \\ \\  \\ \\  \\ \\  \\\\  \\\\ \\  \\\\\\  \\\n"
            + "   \\ \\__\\ \\__\\ \\__\\ \\__\\ \\__\\\\ _\\\\ \\_______\\\n"
            + "    \\|__|\\|__|\\|__|\\|__|\\|__|\\|__|\\|_______|\n";
    private final String horizontalLine = "______________________________________________";
    private String openingMsg = "Heya! I'm Haro!\n" + "What can I do for you today?";
    private String closingMSg = "Bye. Hope to see you some time soon!";

    /**
     * Constructs a Ui instance with a scanner for user input.
     */
    public Ui() {
        this.inputScanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message with a Haro logo.
     */
    public void greet() {
        System.out.println(
                "Greetings from\n" + haroLogo + "\n"
                        + openingMsg + "\n" + horizontalLine);
    }

    /**
     * Displays a farewell message and that the task list has been saved.
     */
    public void bye() {
        System.out.println("List has been saved!");
        System.out.println(closingMSg + "\n"
                + horizontalLine);
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
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Prints all the Tasks within a TaskList.
     *
     * @param tasks TaskList containing tasks to be printed
     */
    public void printList(TaskList tasks) {
        String taskString = tasks.tasksToString();
        if (taskString == "") {
            System.out.println("The task list is currently empty! Add tasks!\n");
        } else {
            System.out.println("Here are the tasks in your list:");
            System.out.println(taskString);
        }
    }

    /**
     * Prints a message for marking a task as done.
     * @param task Task that has been marked
     */
    public void printMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done");
        System.out.println(task.printTask() + "\n");
    }

    /**
     * Prints a message for unMarking a task.
     *
     * @param task Task that has been unmarked
     */
    public void printUnmarkTask(Task task) {
        System.out.println("Alright, I've marked this task as not done yet");
        System.out.println(task.printTask() + "\n");
    }

    /**
     * Prints a message for adding a task.
     *
     * @param task Task that has been added
     * @param taskListSize Current size of the task list after addition of task
     */
    public void printAddTask(Task task, int taskListSize) {
        System.out.println("Got it I've added this task:\n"
                + task.printTask() + "\n"
                + "You now have " + taskListSize + " tasks in the list\n");
    }

    /**
     * Prints a message for deleting a task.
     *
     * @param task          Task that has been deleted
     * @param taskListSize  Current size of the task list after deletion of task
     */
    public void printDeleteTask(Task task, int taskListSize) {
        System.out.println("Noted. I've removed this task");
        System.out.println(task.printTask() + "\n");
        System.out.println("You now have " + taskListSize + " tasks in the list\n");
    }
}
