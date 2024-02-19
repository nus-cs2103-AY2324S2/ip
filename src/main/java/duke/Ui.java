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
        String welcomeMessage = "  " + "Hey! I'm " + name + "\n" +
                "  " + "Is there anything I can do for you?";
        System.out.println(welcomeMessage);
        return welcomeMessage;
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
        return "  " +
                "Error loading file... Creating new empty file";
    }

    /**
     * Prints unmark message.
     *
     * @param task Task that was unmarked.
     */
    public String unmarkMessage(Task task) {
        return "  " + "Ok, I've marked this task " + "as not done yet:" + "\n" + "  " +
                task.toString() + "\n";
    }

    /**
     * Prints mark message.
     *
     * @param task Task that was marked.
     */
    public String markMessage(Task task) {
        return "  " + "Ok, I've marked this task " + "as done:" + "\n" + "  " +
                task.toString() + "\n";
    }

    /**
     * Prints delete message.
     *
     * @param task Task that was deleted.
     */
    public String deleteMessage(Task task) {
        return "  " + "Noted. I've removed this task:" + "\n" + "  " +
                task.toString() + "\n";
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
