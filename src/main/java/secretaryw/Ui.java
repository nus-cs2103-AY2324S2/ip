package secretaryw;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private static final String line = "-----------------------------------------------------------------\n";

    public String showLine() {
        return line;
    }

    public void showGreeting() {
        System.out.println(line + "Whats up, SecretaryW at your service\n" + "How can I help you?\n" + line);
    }

    public void showFarewell() {
        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }

    public void showTasks(ArrayList<Task> taskList) {
        System.out.println(line);
        if (taskList.isEmpty()) {
            System.out.println("No tasks available");
            System.out.println(line);
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + taskList.get(i));
            }
            System.out.println(line);
        }
    }

    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showTaskAdded(Task task, int count) {
        System.out.println(line + "Got it. I've added this task:\n" + task);
        System.out.println(" Now you have " + count + " tasks in the list.\n" + line);
    }

    public void showTaskDeleted(Task task, int count) {
        showLine();
        System.out.println("Noted. I've removed this task:\n" + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
        showLine();
    }
    public void showMessage(String message) {
        System.out.println(line + message + "\n" + line);
    }
}