package duke;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void welcome() {
        printLine();
        System.out.println("Hello! I'm Doye\n" + "What can I do for you?");
        printLine();
    }

    public void bye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
        
    }

    public void list(List<Task> tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task addTask = tasks.get(i);
            System.out.println((i + 1) + "." + addTask.toString());
        }
        printLine();
    }

    public void unmark(Task t) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t.toString());
        printLine();
    }

    public void mark(Task t) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.toString());
        printLine();
    }

    public void showError(DukeException e) {
        printLine();
        System.out.println("OOPS!!! " + e.toString());
        printLine();
    }

    public void dontUnderstand() {
        printLine();
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        printLine();
    }

    public void showLoadingError() {
        printLine();
        System.out.println("loading error");
        printLine();
    }

    public void addedMessage(Task task) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
    }

    public void deletedMessage(Task task) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
    }

    public void totalTask(int count) {
        System.out.println("Now you have " + count + " tasks in the list.");
        printLine();
    }
}