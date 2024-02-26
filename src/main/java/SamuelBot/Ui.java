package SamuelBot;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showWelcome() {
        System.out.println("Hello from SamuelBot!");
        System.out.println("What can I do for you?");
        System.out.println("For adding deadline tasks, your format should be 'deadline return book /by yyyy-MM-dd'.");
        System.out.println("For adding event tasks, your format should be 'event project meeting /from yyyy-MM-dd HH:mm'.");
    }

    public void showTaskAddedConfirmation(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon! Have a nice day!");
    }
}
