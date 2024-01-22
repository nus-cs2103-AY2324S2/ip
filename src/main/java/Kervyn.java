import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
public class Kervyn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> userRequests = new ArrayList<>();
        String chatbotName = "Kervyn";

        System.out.println("\tHello! I'm " + chatbotName);
        System.out.println("\tWhat can I do for you?");

        String userInput;
        do {
            userInput = scanner.nextLine();
            switch (userInput) {
                case "bye":
                    System.out.println("\tBye. Hope to see you again soon!");
                    break;
                case "list":
                    System.out.println("Here are the tasks on your list:");
                    for (int i = 0; i < userRequests.size(); i++) {
                        Task task = userRequests.get(i);
                        char check = task.getStatus() ? 'X' : ' ';
                        System.out.println("\t" + (i + 1) + "." + "[" + check + "] " + task.getName());

                    }
                    break;
                default:
                    System.out.println("\tadded: " + userInput);
                    // Create a new Task for each new item
                    Task newTask = new Task(userInput, false);
                    userRequests.add(newTask);
            }
        } while (!Objects.equals(userInput, "bye"));

    }
}
