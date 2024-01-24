import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void messageWithHorizontalLines(String message) {
        System.out.println("____________________________________________________________\n" +
                           message + "\n" +
                           "____________________________________________________________");
    }

    private static void displayTaskList(ArrayList<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There is no task!");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String botName = "Hammy";
        String welcomeStr = " Hello! I'm " + botName + "\n What can I do for you?";
        String byeStr = "Bye. Hope to see you again soon!";
        ArrayList<String> tasks = new ArrayList<>();

        System.out.println(welcomeStr);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                // If the user enters "bye", exit the loop and say goodbye
                messageWithHorizontalLines(" Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                // If the user enters "list", display the list of tasks
                displayTaskList(tasks);
            } else if ((userInput.equalsIgnoreCase("") || userInput.equalsIgnoreCase(" "))) {
                // If the user enters "list", display the list of tasks
                messageWithHorizontalLines("Please enter valid tasks.");
            } else {
                // For any other input, just display it
                tasks.add(userInput);
                messageWithHorizontalLines(" added: " + userInput);
            }
        }

        scanner.close();
    }
}
