package ui;

import data.Task;
import data.TaskList;
import data.exception.CoDriverException;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void close() {
        this.scanner.close();
    }

    public void showGreeting() {
        showLine();
        System.out.println("Hello! I'm CoDriver, your everyday AI companion!");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void showAddTask(Task task, int size) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size  + " tasks in the list.");
        showLine();
    }

    public void showMarkTask(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        showLine();
    }

    public void showUnmarkTask(Task task) {
        showLine();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(task);
        showLine();
    }

    public void showList(TaskList tasks) {
        showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int listIndex = i + 1;
            System.out.println(listIndex + ". " + tasks.get(i));
        }
        showLine();
    }

    public void showDeleteTask(Task task, int size) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    public void showError(CoDriverException e) {
        showLine();
        System.out.println(e.getMessage());
        showLine();
    }

    public void showNumberFormatError() {
        showLine();
        System.out.println("Error! Argument provided must be a number!");
        showLine();
    }

    public void showFileLoadingError() {

    }

    public void showLine() {
        System.out.println("------------------------------------------------");
    }
}
