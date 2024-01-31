package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    static String name = "Lunaris";
    static String indentedLine = "  _________________________________________________________";

    /**
     * Constructor for Ui.
     *
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Method to print an indented line.
     *
     */
    public void setIndentedLine() {
        System.out.println(indentedLine);
    }

    /**
     * Method to print welcome message.
     *
     */
    public void welcomeMessage() {
        setIndentedLine();
        System.out.println("  " + "Hey! I'm " + name + "\n" +
                "  " + "Is there anything I can do for you?");
        setIndentedLine();
    }

    /**
     * Method to print goodbye message.
     *
     */
    public void goodByeMessage() {
        setIndentedLine();
        System.out.println("  " +
                "Leaving so soon? Alright, have a great day ahead!");
        setIndentedLine();
    }

    /**
     * Method to print loading error message.
     *
     */
    public void loadErrorMessage() {
        setIndentedLine();
        System.out.println("  " +
                "Error loading file... Creating new empty file");
        setIndentedLine();
    }

    /**
     * Method to print unmark message.
     *
     * @param task Task that was unmarked.
     */
    public void unmarkMessage(Task task) {
        setIndentedLine();
        System.out.println("  " + "Ok, I've marked this task " +
                "as not done yet:" + "\n" +
                "  " + task.toString());
        setIndentedLine();
    }

    /**
     * Method to print mark message.
     *
     * @param task Task that was marked.
     */
    public void markMessage(Task task) {
        setIndentedLine();
        System.out.println("  " + "Ok, I've marked this task " +
                "as done:" + "\n" +
                "  " + task.toString());
        setIndentedLine();
    }

    /**
     * Method to print delete message.
     *
     * @param task Task that was deleted.
     */
    public void deleteMessage(Task task) {
        setIndentedLine();
        System.out.println("  " + "Noted. I've removed this task:" +
                "\n" +
                "  " + task.toString());
        setIndentedLine();
    }

    /**
     * Method that prints how many tasks are in the list.
     *
     * @param taskList List to be counted.
     */
    public void listSizeMessage(TaskList taskList) {
        System.out.println("  " +
                "Now you have " + taskList.getSize() + " tasks in the list.");
        setIndentedLine();
    }

    public String readCommandLine() {
        return scanner.nextLine();
    }

    public String readCommand() {
        return scanner.next();
    }

    public int readInt() {
        return scanner.nextInt();
    }
}
