package secretaryw;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private static final String LINE = "-----------------------------------------------------------------\n";

    public String showLine() {
        return LINE;
    }

    public void showGreeting() {
        System.out.println(LINE + "Whats up, SecretaryW at your service\n" + "How can I help you?\n" + LINE);
    }

    public void showFarewell() {
        System.out.println(LINE + "Bye. Hope to see you again soon!\n" + LINE);
    }

    public void showTasks(ArrayList<Task> taskList) {
        System.out.println(LINE);
        if (taskList.isEmpty()) {
            System.out.println("No tasks available");
            System.out.println(LINE);
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + taskList.get(i));
            }
            System.out.println(LINE);
        }
    }

    public String getUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        }
    }

    public void showTaskAdded(Task task, int count) {
        System.out.println(LINE + "Got it. I've added this task:\n" + task);
        System.out.println(" Now you have " + count + " tasks in the list.\n" + LINE);
    }

    public void showTaskDeleted(Task task, int count) {
        showLine();
        System.out.println("Noted. I've removed this task:\n" + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
        showLine();
    }

    public void showMessage(String message) {
        System.out.println(LINE + message + "\n" + LINE);
    }

    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        System.out.println(LINE);
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + matchingTasks.get(i));
            }
        }
        System.out.println(LINE);
    }
}