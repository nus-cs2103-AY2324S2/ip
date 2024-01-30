package Ui;

import java.util.List;
import java.util.Scanner;

import Tasks.Task;

public class Ui {

    final static String HORIZONTAL_LINE = "____________________________________________________________";

    Scanner reader;

    public Ui() {
        this.reader = new Scanner(System.in);
    }

    public String[] getUserCommand() {
        return reader.nextLine().split(" ", 2);
    }

    public void printTaskKeyword(List<Task> userTaskList, String keyword) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < userTaskList.size(); i++) {
            if (userTaskList.get(i).getDescription().contains(keyword)) {
                System.out.println(i + ": " + userTaskList.get(i));
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void printGoodBye() {
        this.printSingleLine("Bye! Hope to see you again soon!");

        reader.close();
    }

    public void printUnknownCommand() {
        this.printSingleLine("Unknown Command!");
    }

    public void printDeleteTask(Task task, int taskSize) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskSize + " tasks in your list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printAddTask(Task task, int taskSize) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task to your list.");
        System.out.println(task);
        System.out.println("Now you have " + taskSize + " tasks in your list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printError(Exception e) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(e.getMessage());
        System.out.println(HORIZONTAL_LINE);
    }

    public void markTask(List<Task> userTaskList, int taskInt) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(userTaskList.get(taskInt).toString());
        System.out.println(HORIZONTAL_LINE);
    }

    public void printList(List<Task> taskList) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.println(i + ": " + taskList.get(i - 1));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void printSingleLine(String msg) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(msg);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printIntro() {
        this.printSingleLine("Hello from Kewgy!\nWhat can I do for you?\nType \"bye\" to exit!");
    }
}
