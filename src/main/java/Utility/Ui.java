package Utility;

import java.util.ArrayList;
import java.util.Scanner;
import Task.Task;

public class Ui {
    Scanner sc = new Scanner(System.in);
    public void greet() {
        System.out.println("Hello! I'm Friendy.");
        System.out.println("What can I do for you?");
    }

    public void farewell() {
        System.out.println("Bye. I will miss you!");
    }

    public void showError(String s) {
        System.out.println(s);
    }

    public void showMark() {
        System.out.println("Setting task as done...");
    }

    public void showUnmark() {
        System.out.println("Setting task as not done...");

    }

    public void showDelete() {
        System.out.println("Deleting task...");
    }

    public void showNumOfTask(int i) {
        if (i == 1) {
            System.out.println("There is 1 task in the list.");
        } else {
            System.out.println("There are " + i + " tasks in the list.");
        }
    }

    public void showAdd() {
        System.out.println("added: ");
    }

    public void showTask(Task t) {
        System.out.println(t.toString());
    }

    public void listTasks(ArrayList<Task> list) {
        int count = 1;
        for (Task t : list) {
            System.out.println(count + "." + t.toString());
            count++;
        }
    }

    public String readInput() {
        return sc.nextLine();
    }
}
