package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {

    protected String botName;
    protected Scanner scanner;

    public Ui(String botName) {
        this.botName = botName;
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLoadingError() {
        System.out.printf("____________________________________________________________%n");
        System.out.printf(" Error loading file. Creating new file...%n");
        System.out.printf("____________________________________________________________%n");

    }
    public void showWelcome() {
        System.out.printf("____________________________________________________________%n");
        System.out.printf(" Hello! I'm %s%n", botName);
        System.out.printf(" What can I do for you?%n");
        System.out.printf("____________________________________________________________%n");

    }

    public void showTaskList(String[] tasks) {
        System.out.print("____________________________________________________________\n");
        System.out.print(" Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.length; i++) {
            System.out.printf(" %d.%s%n", i + 1, tasks[i]);
        }

    }

    public void showTaskMarked(Task task) {
        System.out.printf("____________________________________________________________%n");
        System.out.printf(" Nice! I've marked this task as done:%n");
        System.out.printf("   %s%n", task);

    }

    public void showTaskUnmarked(Task task) {
        System.out.printf("____________________________________________________________%n");
        System.out.printf(" OK, I've marked this task as not done yet:%n");
        System.out.printf("   %s%n", task);

    }
    public void showTaskAdded(Task task, int len) {
        System.out.println("____________________________________________________________\n");
        System.out.println( "Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + len + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int len) {
        System.out.printf("____________________________________________________________%n");
        System.out.printf(" Noted. I've removed this task:%n");
        System.out.printf("   %s%n", task);
        System.out.printf(" Now you have %d tasks in the list.%n", len);
        System.out.printf("____________________________________________________________%n");

    }
    public void showGoodbye() {
        System.out.printf("____________________________________________________________%n");
        System.out.printf(" Bye. Hope to see you again soon!%n");
        System.out.printf("____________________________________________________________%n");

    }

}
