package Utility;

import java.util.ArrayList;
import java.util.Scanner;
import Task.Task;

public class Ui {
    Scanner sc = new Scanner(System.in);

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println("Hello! I'm Friendy.");
        System.out.println("What can I do for you?");
    }

    /**
     * Bids farewell to the user.
     */
    public void farewell() {
        System.out.println("Bye. I will miss you!");
    }

    /**
     * Shows the error found to the user.
     *
     * @param s the error message to show to the user
     */
    public void showError(String s) {
        System.out.println(s);
    }

    /**
     * Indicates task being marked as done.
     */
    public void showMark() {
        System.out.println("Setting task as done...");
    }

    /**
     * Indicates task being marked as undone.
     */
    public void showUnmark() {
        System.out.println("Setting task as not done...");

    }

    /**
     * Indicates task being deleted.
     */
    public void showDelete() {
        System.out.println("Deleting task...");
    }

    /**
     * Shows the number of tasks in the task list.
     *
     * @param i the number of task in the task list
     */
    public void showNumOfTask(int i) {
        if (i == 1) {
            System.out.println("There is 1 task in the list.");
        } else {
            System.out.println("There are " + i + " tasks in the list.");
        }
    }

    /**
     * Indicates task being added.
     */
    public void showAdd() {
        System.out.println("added: ");
    }

    /**
     * Indicates description of the task.
     * @param t the task to be shown
     */
    public void showTask(Task t) {
        System.out.println(t.toString());
    }

    /**
     * Lists a {@link TaskList} to the user.
     *
     * @param list the task list
     */
    public void listTasks(ArrayList<Task> list) {
        int count = 1;
        for (Task t : list) {
            System.out.println(count + "." + t.toString());
            count++;
        }
    }

    /**
     * Reads the next line of input.
     *
     * @return the next line of input
     */
    public String readInput() {
        return sc.nextLine();
    }
}
