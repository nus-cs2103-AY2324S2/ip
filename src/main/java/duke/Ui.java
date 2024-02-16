package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    static String name = "Lunaris";
    static String indentedLine =
            "  _________________________________________________________";

    /**
     * Constructor for Ui.
     *
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints an indented line.
     *
     */
    public void setIndentedLine() {
        System.out.println(indentedLine);
    }

    /**
     * Prints welcome message.
     *
     */
    public void printWelcomeMessage() {
        setIndentedLine();
        System.out.println("  " + "Hey! I'm " + name + "\n" +
                "  " + "Is there anything I can do for you?");
        setIndentedLine();
    }

    /**
     * Prints goodbye message.
     *
     */
    public void printGoodByeMessage() {
        setIndentedLine();
        System.out.println("  " +
                "Leaving so soon? Alright, have a great day ahead!");
        setIndentedLine();
    }

    /**
     * Prints loading error message.
     *
     */
    public void loadErrorMessage() {
        setIndentedLine();
        System.out.println("  " +
                "Error loading file... Creating new empty file");
        setIndentedLine();
    }

    /**
     * Prints unmark message.
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
     * Prints mark message.
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
     * Prints delete message.
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
     * Prints how many tasks are in the list.
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
