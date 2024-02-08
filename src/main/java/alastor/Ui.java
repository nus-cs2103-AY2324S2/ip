package alastor;

import alastor.task.Task;

import java.util.Scanner;

public class Ui {

    protected Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.print("_________________________________________________________________________________________\n");
    }

    public void showGreet() {
        System.out.print("Greetings, mortal! I am Alastor, the Radio Demon at your service.\n"
                + "What desires or inquiries do you bring to my infernal realm?\n");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String message) {
        System.out.print(message + "\n");
    }

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

    public void showMark(Task task, boolean isMark) {
        if (isMark) {
            System.out.print("Well, isn't this delightful! I've marked this task as done, my dear.\n"
                    + "  " + task.toString() + "\n");
        } else {
            System.out.print("Very well, my dear! I've noted this task as yet untouched.\n"
                    + "  " + task.toString() + "\n");
        }
    }

    public void showAdd(Task task, TaskList tasks) {
        System.out.print("Marvelous! Another task graces our repertoire:\n"
                + "  " + task.toString() + "\n"
                + "And with this latest addition, our list of tasks swells to a delightful " + tasks.size() + ".\n");
    }

    public void showDelete(Task task) {
        System.out.print("Very well, my dear! I've removed this task from our list:\n"
                + "  " + task.toString() + "\n");
    }

    public void showExit() {
        System.out.print("Farewell, mortal! Should you ever require my services again, simply summon me.\n");
    }
}
