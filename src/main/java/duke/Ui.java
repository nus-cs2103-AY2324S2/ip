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
     * Prints welcome message.
     *
     */
    public String printWelcomeMessage() {
        return "  " + "Hey! I'm " + name + "\n" +
                "  " + "Is there anything I can do for you?";
    }

    /**
     * Prints goodbye message.
     *
     */
    public String printGoodByeMessage() {
        return "  " +
                "Leaving so soon? Alright, have a great day ahead!";
    }

    /**
     * Prints loading error message.
     *
     */
    public String loadErrorMessage() {
        StringBuilder output = new StringBuilder();
        output.append(indentedLine).append("\n");
        output.append("  " +
                "Error loading file... Creating new empty file");
        output.append(indentedLine);
        return output.toString();
    }

    /**
     * Prints unmark message.
     *
     * @param task Task that was unmarked.
     */
    public String unmarkMessage(Task task) {
        StringBuilder output = new StringBuilder();
        output.append(indentedLine).append("\n");
        output.append("  " + "Ok, I've marked this task " + "as not done yet:" + "\n" + "  ")
                .append(task.toString()).append("\n");
        output.append(indentedLine);
        return output.toString();
    }

    /**
     * Prints mark message.
     *
     * @param task Task that was marked.
     */
    public String markMessage(Task task) {
        StringBuilder output = new StringBuilder();
        output.append(indentedLine).append("\n");
        output.append("  " + "Ok, I've marked this task " + "as done:" + "\n" + "  ")
                .append(task.toString()).append("\n");
        output.append(indentedLine);
        return output.toString();
    }

    /**
     * Prints delete message.
     *
     * @param task Task that was deleted.
     */
    public String deleteMessage(Task task) {
        StringBuilder output = new StringBuilder();
        output.append(indentedLine).append("\n");
        output.append("  " + "Noted. I've removed this task:" + "\n" + "  ")
                .append(task.toString()).append("\n");
        output.append(indentedLine);
        return output.toString();
    }

    /**
     * Prints how many tasks are in the list.
     *
     * @param taskList List to be counted.
     */
    public String listSizeMessage(TaskList taskList) {
        return "  " +
                "Now you have " + taskList.getSize() + " tasks in the list.";
    }

    public String listTaskMessage(TaskList taskList) {
        return taskList.listTask();
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
