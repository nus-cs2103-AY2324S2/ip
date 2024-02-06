package duke;
import java.util.Scanner;

import task.Task;

import java.util.ArrayList;

public class Ui {
    private final String LINE = "\t____________________________________________________________";
    private Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void sayGreetings() {
        this.showLine();
        System.out.println("\tHello! I'm SKY");
        System.out.println("\tWhat can I do for you?");
        this.showLine();
    }

    public void sayBye() {
        System.out.println("\tBye. Hope to see you again soon!");
        this.showLine();
    }

    public void showErrorMessage(Exception e) {
        System.out.println("\t" + e);
        this.showLine();
    }

    public void showAddTask(Task task, int size) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        this.showLine();
    }

    public void showList(ArrayList<Task> list) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + list.get(i));
        }
        this.showLine();
    }

    public void showMarkTask(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task);
        this.showLine();
    }

    public void showUnmarkTask(Task task) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + task);
        this.showLine();
    }

    public void showDeleteTask(Task task, int size) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        this.showLine();
    }

    public void showCorruptedData() {
        System.out.println("\tData file is corrupted. Starting with an empty list.");
        this.showLine();
    }
}
