import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getUserInput() {
        return SCANNER.nextLine().trim();
    }

    public static void showWelcomeMessage() {
        System.out.println("Hello! I'm Georgie.");
        System.out.println("What can I do for you?");
    }

    public static void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showError(String errorMessage) {
        System.out.println("Oops! " + errorMessage);
    }

    public static void showTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here " + (tasks.size() == 1 ? "is the task" : "are the tasks") + " in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).getStatusIcon());
            }
        }
    }
}
