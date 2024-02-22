package Bentley;
// Ui.java
import java.util.Scanner;
import java.util.ArrayList;


public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Bentley\n" + "What can I do for you?");
    }

    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showTaskAddedMessage(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }
}