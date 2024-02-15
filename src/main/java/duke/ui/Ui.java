package duke.ui;

import java.util.ArrayList;

import duke.task.Task;

/**
 * This class displays the messages and error to the user.
 * @author Tang Hao Liang
 */
public class Ui {

    /**
     * Constructor to start the scanning of user inputs.
     */
    public Ui() {
    }

    /**
     * Returns the welcome message.
     * @return Display text for welcome.
     */
    public static String showWelcome() {
        return "Hello! I'm Unknown \n"
                + "What can I do for you?";
    }

    /**
     * Returns the end message.
     * @return Display text for end.
     */
    public static String showEnd() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the loading error message.
     * @return Display text for loading error.
     */
    public static String showLoadingError() {
        return "Error when loading file";
    }

    /**
     * Returns string of all tasks in the list.
     *
     * @param taskList List of tasks.
     * @return Display text for list of tasks.
     */
    public static String printList(ArrayList<Task> taskList) {
        //Function to produce the string for the list to be printed
        String out = "Here are the tasks in your list:\n";
        int lastNumber = taskList.size() + 1;
        for (int i = 1; i < lastNumber; i++) {
            out += i + "." + taskList.get(i - 1) + "\n";
        }

        return out;
    }

    /**
     * Returns string when tasks has been added.
     *
     * @param task Added task.
     * @param size Size of List.
     * @return Display text of added task.
     */
    public static String printAdd(String task, int size) {
        return "Got it. I've added this task: \n" + task + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Returns string when tasks are marked.
     *
     * @param task Marked Task.
     * @return Display text of mark.
     */
    public static String printMark(String task) {
        return "Nice! I've marked this task as done\n" + "  " + task + "\n";
    }

    /**
     * Returns string when tasks are unmarked.
     *
     * @param task Unmarked Task.
     * @return Display text of unmark.
     */
    public static String printUnmark(String task) {
        return "OK, I've marked this task as not done yet\n" + "  " + task;
    }

    /**
     * Returns string when tasks are deleted.
     *
     * @param task Deleted Task.
     * @param num Number of remaining task.
     * @return Display text of delete.
     */
    public static String printDelete(String task, int num) {
        return "Noted. I've removed this task: \n" + task + "\nNow you have " + num + " tasks in the list.";
    }
}
