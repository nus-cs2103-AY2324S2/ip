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

    public void showAddTask(Task newTask, TaskList tasks) {
        System.out.println("Got it. I've added this task:\n"
                + newTask + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }

    public void showDeleteTask(int index, TaskList tasks) {
        System.out.println("Noted. I've removed this task:\n"
                + tasks.get(index).toString() + "\n"
                + "Now you have " + (tasks.size() - 1) + " tasks in the list.");
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showMarkTask(TaskList tasks, int index) {
        System.out.println("Nice! I've marked this task as done:\n"
                + tasks.get(index).toString());
    }

    public void showUnMarkTask(TaskList tasks, int index) {
        System.out.println("OK, I've marked this task as not done yet:\n"
                + tasks.get(index).toString());
    }

    public void showTaskList(TaskList tasks) {
        int n = tasks.size();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < n; i++) {
            Task currentTask = tasks.get(i);
            System.out.println((i + 1) + "." + currentTask.toString());
        }
    }

    public void showError(String inputString) {
        System.out.println(inputString);
    }

    public void showEcho(String inputString) {
        System.out.println(inputString);
    }

    public void showLoadingError() {
        System.out.println(new StorageFileLoadingException().getMessage());
    }
}
