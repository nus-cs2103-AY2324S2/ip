package duke.ui;

import duke.task.TaskList;

import java.io.InputStream;

import java.util.Scanner;

/**
 * Handles UI of the application.
 */
public class Ui {
    Scanner in;
    String input;
    private static final String LINE = "    ____________________________________________________________";
    private static final String TAB = "  ";

    /**
     * Constructor of UI object.
     *
     * @param in InputStream to read input from.
     */
    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    /**
     * Fetches the first keyword of user input as command.
     *
     * @return Command.
     */
    public String getCommand() {
        return in.next();
    }

    /**
     * Fetches the detail and description of the command.
     *
     * @return Command description.
     */
    public String getCommandDescription() {
        return in.nextLine().trim();
    }

    /**
     * Prints the horizontal line divider.
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints the input message.
     *
     * @param message Message to print.
     */
    public void printMessage(String message) {
        System.out.printf("     %s\n", message);
    }

    /**
     * Prints the message for adding a task.
     *
     * @param newTask String representation of added task.
     */
    public void printAddTask(String newTask) {
        printLine();
        printMessage("Got it. I've added this task:");
        printMessage(TAB + newTask);
        printLine();
    }

    /**
     * Prints the message for marking a task.
     *
     * @param task String representation of marked task.
     */
    public void printMark(String task) {
        printLine();
        printMessage("Nice! I've marked this task as done:");
        printMessage(TAB + task);
        printLine();
    }

    /**
     * Prints the message for unmarking a task.
     *
     * @param task String representation of unmarked task.
     */
    public void printUnmark(String task) {
        printLine();
        printMessage("OK, I've marked this task as not done yet:");
        printMessage(task);
        printLine();
    }

    /**
     * Prints the message for deleting a task.
     *
     * @param task String representation of deleted task.
     */
    public void printDelete(String task, int size) {
        printLine();
        printMessage("Got it. I've removed this task:");
        printMessage(TAB + task);
        printMessage("Now you have " + size + " tasks in the list!");
        printLine();
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks String representation of the task list.
     */
    public void printList(TaskList tasks) {
        printLine();
        printMessage("Here are the tasks in your list:");
        System.out.print(tasks.toString());
        printLine();
    }

    /**
     * Prints greeting message.
     */
    public void printGreetings() {
        printLine();
        printMessage("Hello, I'm Buto!");
        printMessage("What can I do for you?");
        printLine();
    }

    /**
     * Prints goodbye message.
     */
    public void printGoodbye() {
        in.close();
        printLine();
        printMessage("Bye. Hope to see you again soon!");
        printLine();
    }
}
