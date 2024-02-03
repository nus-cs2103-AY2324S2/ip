package duke.ui;

import duke.task.TaskList;

import java.io.InputStream;

import java.util.Scanner;

public class Ui {
    Scanner in;
    String input;
    private static final String LINE = "    ____________________________________________________________";
    private static final String TAB = "  ";
    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    public String getCommand() {
        return in.next();
    }

    public String getCommandDescription() {
        return in.nextLine().trim();
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public void printMessage(String message) {
        System.out.printf("     %s\n", message);
    }

    public void printAddTask(String newTask) {
        printLine();
        printMessage("Got it. I've added this task:");
        printMessage(TAB + newTask);
        printLine();
    }

    public void printMark(String task) {
        printLine();
        printMessage("Nice! I've marked this task as done:");
        printMessage(TAB + task);
        printLine();
    }

    public void printUnmark(String task) {
        printLine();
        printMessage("OK, I've marked this task as not done yet:");
        printMessage(task);
        printLine();
    }

    public void printDelete(String task, int size) {
        printLine();
        printMessage("Got it. I've removed this task:");
        printMessage(TAB + task);
        printMessage("Now you have " + size + " tasks in the list!");
        printLine();
    }

    public void printList(TaskList tasks) {
        printLine();
        printMessage("Here are the tasks in your list:");
        System.out.print(tasks.toString());
        printLine();
    }

    public void printGreetings() {
        printLine();
        printMessage("Hello, I'm Buto!");
        printMessage("What can I do for you?");
        printLine();
    }

    public void printGoodbye() {
        in.close();
        printLine();
        printMessage("Bye. Hope to see you again soon!");
        printLine();
    }
}
