import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Georgie";
        ArrayList<Task> tasks = new ArrayList<>();
        int taskCounter = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");

        while (true) {
            try {
                String userInput = scanner.nextLine().trim();
                CommandHandler.handleCommand(userInput, tasks, taskCounter);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}