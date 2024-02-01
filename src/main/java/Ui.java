import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Jerry.\nWhat can I do for you?");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

    public void showList(TaskList list) {
        ArrayList<Task> tasks = list.getTasks();
        System.out.println("Here are the tasks in your list:");
        for (int x = 0; x < tasks.size() ; x++) {
            System.out.println((x + 1) + "." + tasks.get(x));
        }
    }

    public void showMark(TaskList list, int taskIndex) {
        ArrayList<Task> tasks = list.getTasks();
        System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(taskIndex));
    }

    public void showUnmark(TaskList list, int taskIndex) {
        ArrayList<Task> tasks = list.getTasks();
        System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(taskIndex));
    }

    public void showDelete(TaskList list, int taskIndex) {
        ArrayList<Task> tasks = list.getTasks();
        Task removedTask = tasks.get(taskIndex);
        System.out.println("I've removed this task:\n  " + removedTask);
        System.out.println("Now you have " + (tasks.size()) + " tasks in the list.");
    }

    public void showAdded(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:\n  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
