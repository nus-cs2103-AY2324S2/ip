package duke.ui;

import duke.task.TaskList;

import java.io.InputStream;

import java.util.List;
import java.util.Scanner;

/**
 * Handles UI of the application.
 */
public class Ui {
    Scanner in;
    String input;
    private static final String LINE = "    ____________________________________________________________\n";
    private static final String TAB = "  ";

    /**
     * Constructs a UI object.
     *
     * @param in InputStream to read input from.
     */
    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    public Ui () { }

    public Ui(String in) {
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

    public String getGreetings() {
        StringBuilder sb = new StringBuilder();
        sb.append(getMessage("Hello, I'm Buto!"))
                        .append(getMessage("What can I do for you?"));
        return sb.toString();
    }

    public String getMessage(String message) {
        return String.format("     %s\n", message);
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

    public String getAddTaskMessage(String newTask) {
        return getMessage("Got it. I've added this task:")
                + getMessage(TAB + newTask);
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

    public String getMarkMessage(String task) {
        return getMessage("Nice! I've marked this task as done:")
                + getMessage(TAB + task);
    }

    /**
     * Prints the message for unmarking a task.
     *
     * @param task String representation of unmarked task.
     */
    public void printUnmark(String task) {
        printLine();
        printMessage("OK, I've marked this task as not done yet:");
        printMessage(TAB + task);
        printLine();
    }

    public String getUnmarkMessage(String task) {
        return getMessage("OK, I've marked this task as not done yet:")
                + getMessage(TAB + task);
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

    public String getDeleteMessage(String task, int size) {
        return getMessage("Got it. I've removed this task:")
                + getMessage(TAB + task)
                + getMessage("Now you have " + size + " tasks in the list!");
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

    public String getLists(TaskList tasks) {
        return getMessage("Here are the tasks in your list:")
                + tasks.toString();
    }

    /**
     * Prints message for finding tasks matching a keyword.
     *
     * @param tasks String representation of the tasks matching the given keyword.
     */
    public void printFind(String tasks) {
        printLine();
        printMessage("Here are the matching tasks in your list:");
        System.out.print(tasks);
        printLine();
    }

    public String getFindMessage(String tasks) {
        return getMessage("Here are the tasks in your list:")
                + tasks;
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

    public String getGoodbye() {
        return getMessage("Bye. Hope to see you again soon!");
    }
}
