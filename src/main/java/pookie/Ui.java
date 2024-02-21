package pookie;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import pookie.tasks.Task;
import pookie.tasks.TaskList;

/**
 * Represents the user interface of the application.
 */
public class Ui {

    public static final String DIVIDER = "____________________________________________________________\n";
    public static final String INTRO = "yoooooooo im sibehupzcoder9000\n"
            + "what you want sia";
    public static final String OUTRO = "toodledoo";

    public final Scanner in;
    private final PrintStream out;

    /**
     * Constructor for the user interface.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructor for the user interface.
     *
     * @param in The input stream to be used.
     * @param out The output stream to be used.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /** Shows message(s) to the user */
    public void showToUser(String... message) {
        for (String m : message) {
            out.println(m);
        }
    }

    /** Shows intro message to the user */
    public void showIntro() {
        showToUser(DIVIDER, INTRO, DIVIDER);
    }

    /** Shows outro message to the user */
    public void showOutro() {
        showToUser(DIVIDER, OUTRO, DIVIDER);
    }

    /** Shows loading error message to the user */
    public void showLoadingError(String e) {
        showToUser(DIVIDER, e, DIVIDER);
    }

    /**
     * Shows the list of pookie tasks to the user.
     *
     * @param list The list of pookie tasks to be shown.
     */
    public void showList(TaskList list) {
        String message = "Here are the tasks in your list";
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < list.getSize(); i++) {
            tasks.append(" ").append(i + 1).append(". ").append(list.getTask(i).toString()).append("\n");
        }
        showToUser(DIVIDER, message, tasks.toString(), DIVIDER);
    }

    /**
     * Returns message string for "delete" action.
     *
     * @param i index of task to delete.
     */
    public void deleteMessage(int i, TaskList list) {
        String m1 = "I remove this one alrdy: \n";
        String m2 = "\n Now you have " + (list.getSize() - 1) + " tasks in the list.\n";
        showToUser(DIVIDER, m1, list.getTask(i - 1).toString(), m2, DIVIDER);
    }

    /**
     * Returns message string for "add" action.
     *
     * @param task new task to add to list.
     */
    public void addMessage(Task task, TaskList list) {
        String m1 = " Got it. I've added this task:\n";
        String m2 = "\n Now you have " + (list.getSize()) + " tasks in the list.\n";
        showToUser(DIVIDER, m1, task.toString(), m2, DIVIDER);
    }

}
