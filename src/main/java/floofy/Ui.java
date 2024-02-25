package floofy;

import floofy.task.Task;

import java.util.Scanner;

/**
 * Represents the user interface of the chat-bot.
 */
public class Ui {

    /** The scanner to read user input. */
    protected Scanner scanner;

    /** The line to separate different sections of the chat-bot. */
    private final String line = "---------------------------";

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Wraps the given string with a line above and below it.
     */
    public String wrapWithlines(String str) {
        return line + "\n" + str + "\n" + line;
    }

    /**
     * Shows the error message on the ui when loading tasks from file fails.
     */
    public void showLoadingError() {
        String s = "An error occurred while loading tasks from file.";
        System.out.println(wrapWithlines(s));
    }

    /**
     * Shows the welcome message on the ui when the chat-bot first runs.
     */
    public String showWelcomeMsg() {
        String s1 = "Hello! I'm Floofy!";
        String s2 = "What can I do for you?";
        return wrapWithlines(s1 + "\n" + s2);
    }

    /**
     * Shows the marked task on the ui.
     */
    public String showMarkedTask(Task task) {
        String msg = "Nice! I've marked this task as done:";
        String markedTask = task.toString();
        return wrapWithlines(msg + "\n" + markedTask);
    }

    /**
     * Shows the unmarked task on the ui.
     */
    public String showUnmarkedTask(Task task) {
        String msg = "OK, I've marked this task as not done yet:";
        String unmarkedTask = task.toString();
        return wrapWithlines(msg + "\n" + unmarkedTask);
    }

    /**
     * Shows the added task on the ui.
     */
    public String showAddedTask(Task task, int len) {
        String startMsg = "Got it. I've added this task:";
        String addedTask = task.toString();
        String endMsg = "Now you have " + len + " tasks in the list.";
        return wrapWithlines(startMsg + "\n" + addedTask + "\n" + endMsg);
    }

    /**
     * Shows the deleted task on the ui.
     */
    public String showDeletedTask(Task task, int len) {
        String startMsg = "Noted. I've removed this task:";
        String deletedTask = task.toString();
        String endMsg = "Now you have " + len + " tasks in the list.";
        return wrapWithlines(startMsg + "\n" + deletedTask + "\n" + endMsg);
    }

    /**
     * Shows the list of tasks on the ui.
     */
    public String showTaskList(TaskList list) {
        String output = "";
        for (int i = 0; i < list.getSize(); i++) {
            String numberedOutput = String.format("%d. %s", i + 1, list.getTask(i).toString());
            output += numberedOutput + "\n";
        }
        return wrapWithlines(output);
    }

    /**
     * Shows the list of matching tasks on the ui.
     */
    public String showMatchingTasks(TaskList list) {
        String msg = "Here are the matching tasks in your list:";
        String output = "";
        for (int i = 0; i < list.getSize(); i++) {
            String numberedOutput = String.format("%d. %s", i + 1, list.getTask(i).toString());
            output += numberedOutput + "\n";
        }
        return wrapWithlines(msg + "\n" + output);
    }

    /**
     * Shows the goodbye message on the ui when the user wishes to exit the chat-bot.
     */
    public String showGoodbyeMsg() {
        String msg = "Bye. Hope to see you again soon!";
        return wrapWithlines(msg);
    }

}
