package alastor;

import java.util.Scanner;

import alastor.task.Task;

/**
 * Represents the user interface of the application.
 */
public class Ui {

    protected static boolean dirCreated = false;
    protected static boolean fileCreated = false;
    protected Scanner sc;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Shows the greeting message.
     */
    public String showGreet() {
        String temp = "Greetings, mortal! I am Alastor, the Radio Demon at your service.\n"
                + "What desires or inquiries do you bring to my infernal realm?\n";
        if (dirCreated) {
            temp += "I've created a new directory for your tasks, my dear.\n";
            dirCreated = false;
        }
        if (fileCreated) {
            temp += "I've created a new file for your tasks, my dear.\n";
            fileCreated = false;
        }
        return temp;
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public String showList(TaskList tasks) {
        String temp = "Behold, my dear! Here unfurls the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            temp += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return temp;
    }

    /**
     * Shows the tasks that match the keyword.
     *
     * @param tasks The list of tasks that match the keyword.
     */
    public String showFind(TaskList tasks) {
        if (tasks.size() == 0) {
            return "I'm afraid I couldn't find any tasks that match your keyword, my dear.\n";
        }
        String temp = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            temp += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return temp;
    }

    /**
     * Shows the mark of the task.
     *
     * @param task The task to be marked.
     * @param isMark The boolean value of whether the task is marked.
     */
    public String showMark(Task task, boolean isMark) {
        if (isMark) {
            return "Well, isn't this delightful! I've marked this task as done, my dear.\n"
                    + "  " + task.toString() + "\n";
        } else {
            return "Very well, my dear! I've noted this task as yet untouched.\n"
                    + "  " + task.toString() + "\n";
        }
    }

    /**
     * Shows the add of the task.
     *
     * @param task The task to be added.
     * @param tasks The list of tasks.
     */
    public String showAdd(Task task, TaskList tasks) {
        return "Marvelous! Another task graces our repertoire:\n"
                + "  " + task.toString() + "\n"
                + "And with this latest addition, our list of tasks swells to a delightful " + tasks.size() + ".\n";
    }

    /**
     * Shows the deletion of the task.
     *
     * @param task The task to be deleted.
     */
    public String showDelete(Task task) {
        return "Very well, my dear! I've removed this task from our list:\n"
                + "  " + task.toString() + "\n";
    }

    /**
     * Shows the exit message.
     */
    public String showExit() {
        return "Farewell, mortal! Should you ever require my services again, simply summon me.\n";
    }
}
