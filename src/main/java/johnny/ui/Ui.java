package johnny.ui;

import johnny.exceptions.JohnnyException;
import johnny.tasks.Task;
import johnny.tasks.TaskList;

import java.util.Scanner;

public class Ui {

    private Scanner scanner = new Scanner(System.in);

    public void showError(String errorMessage) {
        System.out.println(errorMessage + "\n");
    }

    public void showWelcome() {
        System.out.println("Johnny here. What do you want bro?\n");
    }

    public void showEnd() {
        scanner.close();
        System.out.println("Bye bro. I'm going back to sleep.");
    }

    public void showList(TaskList tasks) throws JohnnyException {
        System.out.println("Get all these done bro:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
        System.out.println();
    }

    public void showMark(Task task) {
        System.out.println("Finally done bro.");
        System.out.println(task + "\n");
    }

    public void showUnmark(Task task) {
        System.out.println("Why are you not done yet bro?");
        System.out.println(task + "\n");
    }

    public void showDelete(Task task, TaskList tasks) {
        System.out.println("Task removed. Why so lazy bro?");
        System.out.println(task);
        System.out.println("You still have " + tasks.size() + " tasks in your list bro.\n");
    }

    public void showAddTask(Task task, TaskList tasks) {
        System.out.println("Go get this done bro:");
        System.out.println(task);
        System.out.println("You still have " + tasks.size() + " tasks in your list bro.\n");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

}
