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
        System.out.println("   ____________________________________________________________");
        System.out.println("    Hello! I'm BotYue");
        System.out.println("    What can I do for you?");
        System.out.println("   ____________________________________________________________");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("   ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
        }

        System.out.println("   ____________________________________________________________");
    }

    public void showMarkedMessage(Task task) {
        System.out.println("   ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
        System.out.println("   ____________________________________________________________");
    }

    public void showUnmarkedMessage(Task task) {
        System.out.println("   ____________________________________________________________");
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + task);
        System.out.println("   ____________________________________________________________");
    }

    public static void showDeletedMessage(Task task, int count) {
        System.out.println("   ____________________________________________________________");
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);

        System.out.println("    Now you have " + count + " tasks in the list.");
        System.out.println("   ____________________________________________________________");
    }
    public static void showGoodbyeMessage() {
        System.out.println("   ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("   ____________________________________________________________");
    }


    public void showAddedMessage(Task task, int count) {
        System.out.println("   ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + count + " tasks in the list.");
        System.out.println("   ____________________________________________________________");
    }

    public void showError(String message) {

        System.out.println("   ____________________________________________________________");
        System.out.println("   " + message);
        System.out.println("   ____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }


}

