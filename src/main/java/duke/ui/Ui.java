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
     * Returns the help message
     * @return Display text for help.
     */
    public static String showHelp() {
        return "Brief information of the builtin commands for the application\n\n"
                + "Command summary (All date and time should be in yyyy-MM-dd HH:mm): \n\n"
                + "- todo [Todo description]\n"
                + "     Add task that you want to do without deadline\n"
                + "     eg. todo Read book\n\n"
                + "- deadline [Deadline description] /by [Date and Time]\n"
                + "     Add task that you want to do with deadline\n"
                + "     eg. deadline Return book /by 2019-12-12 12:00\n\n"
                + "- event [Event description] /from [Start date and time] /to [End date and time]\n"
                + "     Add event with a duration\n"
                + "     eg. event Project meeting /from 2019-12-12 12:00 /to 2019-12-12 16:00\n\n"
                + "- list\n"
                + "     List all tasks that you have inputted\n\n"
                + "- mark [Task number]\n"
                + "     Mark tasks that you have done\n"
                + "     eg. mark 1\n\n"
                + "- unmark [Task number]\n"
                + "     Unmark tasks that you have done previously but requires further changes\n"
                + "     eg. unmark 1\n\n"
                + "- delete [Task number]\n"
                + "     Delete tasks that are done or not required anymore\n"
                + "     eg. delete 1\n\n"
                + "- find [Keyword]\n"
                + "     Find specific tasks with the common keyword\n"
                + "     eg. find book\n\n"
                + "- bye\n"
                + "     Quits the application";
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
