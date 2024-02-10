package alastor;

import java.util.Scanner;

import alastor.task.Task;

/**
 * Represents the user interface of the application.
 */
public class Ui {

    protected Scanner sc;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Shows the line.
     */
    public void showLine() {
        System.out.print("_________________________________________________________________________________________\n");
    }

    /**
     * Shows the greeting message.
     */
    public void showGreet() {
        System.out.print("Greetings, mortal! I am Alastor, the Radio Demon at your service.\n"
                + "What desires or inquiries do you bring to my infernal realm?\n");
    }

    /**
     * Reads the command from the user.
     *
     * @return The command from the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows the error message.
     *
     * @param message The error message.
     */
    public void showError(String message) {
        System.out.print(message + "\n");
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public void showList(TaskList tasks) {
        System.out.print("Behold, my dear! Here unfurls the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
    }

    /**
     * Shows the tasks that match the keyword.
     *
     * @param tasks The list of tasks that match the keyword.
     */
    public void showFind(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.print("I'm afraid I couldn't find any tasks that match your keyword, my dear.\n");
            return;
        }
        System.out.print("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
    }

    /**
     * Shows the mark of the task.
     *
     * @param task The task to be marked.
     * @param isMark The boolean value of whether the task is marked.
     */
    public void showMark(Task task, boolean isMark) {
        if (isMark) {
            System.out.print("Well, isn't this delightful! I've marked this task as done, my dear.\n"
                    + "  " + task.toString() + "\n");
        } else {
            System.out.print("Very well, my dear! I've noted this task as yet untouched.\n"
                    + "  " + task.toString() + "\n");
        }
    }

    /**
     * Shows the add of the task.
     *
     * @param task The task to be added.
     * @param tasks The list of tasks.
     */
    public void showAdd(Task task, TaskList tasks) {
        System.out.print("Marvelous! Another task graces our repertoire:\n"
                + "  " + task.toString() + "\n"
                + "And with this latest addition, our list of tasks swells to a delightful " + tasks.size() + ".\n");
    }

    /**
     * Shows the deletion of the task.
     *
     * @param task The task to be deleted.
     */
    public void showDelete(Task task) {
        System.out.print("Very well, my dear! I've removed this task from our list:\n"
                + "  " + task.toString() + "\n");
    }

    /**
     * Shows the exit message.
     */
    public void showExit() {
        System.out.print("Farewell, mortal! Should you ever require my services again, simply summon me.\n");
    }
}
