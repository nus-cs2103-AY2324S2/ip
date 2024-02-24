package raphael.ui;

import java.util.Scanner;

import raphael.Raphael;
import raphael.task.Task;
import raphael.task.TaskList;

/**
 * The class for user interface.
 */
public class Ui {
    private static final String START_LINE = "----------------"
            + "------------[Rep"
            + "ort]------------"
            + "----------------";

    private static final String END_LINE = "----------------"
            + "------------[End"
            + "ing]------------"
            + "----------------";
    private static final String WARNING_LINE = "----------------"
            + "-----------<WARN"
            + "ing!>-----------"
            + "----------------";
    private static final String DIVIDER = "----------------"
            + "----------------"
            + "----------------"
            + "----------------";

    private String savedOutput = Raphael.LOGO;
    /**
     * Prints the starting line that acts as the upper border of the output.
     */
    private static void printStartLine() {
        System.out.print(Ui.START_LINE);
    }

    /**
     * Prints the ending line that acts as the lower border of the output.
     */
    private static void printEndLine() {
        System.out.println(Ui.END_LINE);
    }

    /**
     * Prints the line indicating that the following output is an error message.
     */
    private static void printWarningLine() {
        System.out.print(Ui.WARNING_LINE);
    }

    /**
     * The divider line between outputs.
     */
    public void showLine() {
        System.out.println(Ui.DIVIDER);
    }

    /**
     * Prints the given input message.
     *
     * @param errorMessage the error message that has to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints the output message when loading the tasks from tasks file into the task list.
     */
    public void showLoadingError() {
        System.out.println("Failed to load task list!");
    }

    /**
     * Prints the welcome message upon activation of Raphael.
     */
    public void showWelcome() {
        System.out.println(raphael.Raphael.LOGO);
        System.out.printf("Hi! I am %s, how can I help you?\n", raphael.Raphael.BOT_NAME);
    }

    /**
     * Prints the task that has been added into the Task List.
     *
     * @param tasks the task list.
     * @param task the task that need to be added.
     */
    public void showAddOutput(TaskList tasks, Task task) {
        String output = String.format("Roger that! I have added the following task into your list:\n"
                + "\t%s\n%s", task, tasks.getSize());
        this.savedOutput = output;
        System.out.println(output);
    }

    /**
     * Prints the output when find command is used.
     *
     * @param text the text to be print when find command is used.
     */
    public void showFindOutput(String text) {
        this.savedOutput = text;
        System.out.println(text);
    }

    /**
     * Prints the output when delete command is used.
     *
     * @param task the task to be deleted.
     */
    public void showDeleteOutput(Task task) {
        String output = String.format("Alrigthy! I have deleted the following task for you:\n"
                + "\t%s", task);
        this.savedOutput = output;
        System.out.println(output);
    }

    /**
     * Prints the output when the list command is used.
     *
     * @param text the text to be print when list command is used.
     */
    public void showListOutput(String text) {
        this.savedOutput = text;
        System.out.println(text);
    }

    /**
     * Provides output for marking task as done.
     *
     * @param text the output.
     */
    public void showMarkOutput(String text) {
        this.savedOutput = text;
        System.out.println(text);
    }
    /**
     * Provides output for marking task as undone.
     *
     * @param text the output.
     */
    public void showUnmarkOutput(String text) {
        this.savedOutput = text;
        System.out.println(text);
    }
    /**
     * The farewell message from Raphael upon termination.
     */
    public static void end() {
        final String endings = "Bye. It is an honor to serve you.\n"
                + "Hope to see you again soon!";
        System.out.println(endings);
        Ui.printEndLine();
    }

    /**
     * Read inputs from the command line.
     * @return the input read.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public void saveOutput(String output) {
        this.savedOutput = output;
    }
    public String getOutput() {
        return this.savedOutput;
    }
}
