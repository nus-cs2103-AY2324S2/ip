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
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
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
