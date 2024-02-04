package duke;

import tasks.Task;
import tasks.TaskList;

import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintStream;
public class Ui {

    public static final String DIVIDER = "____________________________________________________________\n";

    public static final String INTRO = "yoooooooo im sibehupzcoder9000\n" +
            "what you want sia";

    public static final String OUTRO = "toodledoo";

    public final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

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

    public void showIntro() {
        showToUser(DIVIDER, INTRO, DIVIDER);
    }

    public void showOutro() {
        showToUser(DIVIDER, OUTRO, DIVIDER);
    }

    public void showLoadingError(String e) {
        showToUser(DIVIDER, e, DIVIDER);
    }

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
