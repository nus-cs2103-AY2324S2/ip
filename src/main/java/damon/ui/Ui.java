package damon.ui;


import damon.task.Task;
import damon.tasklist.TaskList;
import damon.exceptions.StorageFileLoadingException;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        //Solution below adapted from https://stackoverflow.com/a/16252290
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____\n"
                + "|  _  \\\n"
                + "| | | |\n"
                + "| |_| |\n"
                + "|____/ \n";
        System.out.println("Hello from\n" + logo);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showAddCommand(Task newTask, TaskList tasks) {
        System.out.println("Got it. I've added this task:\n"
                + newTask + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public void showDeleteCommand(int index, TaskList tasks) {
        System.out.println("Noted. I've removed this task:\n"
                + tasks.getTask(index).toString() + "\n"
                + "Now you have " + (tasks.getSize() - 1) + " tasks in the list.");
    }

    public void showExitCommand() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showMarkCommand(TaskList tasks, int index) {
        System.out.println("Nice! I've marked this task as done:\n"
                + tasks.getTask(index).toString());
    }

    public void showUnMarkCommand(TaskList tasks, int index) {
        System.out.println("OK, I've marked this task as not done yet:\n"
                + tasks.getTask(index).toString());
    }

    public void showShowTaskListCommand(TaskList tasks) {
        int n = tasks.getSize();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < n; i++) {
            Task currentTask = tasks.getTask(i);
            System.out.println((i + 1) + "." + currentTask.toString());
        }
    }

    public void showEchoCommand(String inputString) {
        System.out.println(inputString);
    }

    public void showError(String inputString) {
        System.out.println(inputString);
    }

    public void showLoadingError() {
        System.out.println(new StorageFileLoadingException().getMessage());
    }
}
