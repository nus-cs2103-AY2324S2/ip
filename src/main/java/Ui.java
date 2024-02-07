import java.util.Scanner;
import java.util.ArrayList;
public class Ui {
    protected String botName;
    protected Scanner scanner;
    private String line = "__________________________________________________";
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcomeMsg() {
        System.out.println(line);
        System.out.println("Hello! I'm Floofy!");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void showMarkedTask(Task task) {
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println(line);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println(line);
    }

    public void showAddedTask(Task task, int len) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + len + " tasks in the list.");
        System.out.println(line);
    }

    public void showDeletedTask(Task task, int len) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + len + " tasks in the list.");
        System.out.println(line);
    }

    public void showTaskList(ArrayList<Task> list) {
        System.out.println(line);
        for (int i = 0; i < list.size(); i++) {
            String numberedOutput = String.format("%d. %s", i + 1, list.get(i).toString());
            System.out.println(numberedOutput);
        }
        System.out.println(line);
    }

    public void showGoodbyeMsg() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

}
