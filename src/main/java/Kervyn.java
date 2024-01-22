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
        char check;
        String userInput;
        Task task;
        do {
            userInput = scanner.nextLine();
            // To check for case when input is mark/unmark
            String[] processedUserInput = userInput.split(" ");
            switch (processedUserInput[0]) {
                case "bye":
                    System.out.println("\tBye. Hope to see you again soon!");
                    break;
                case "list":
                    System.out.println("\tHere are the tasks on your list:");
                    for (int i = 0; i < userRequests.size(); i++) {
                        task = userRequests.get(i);
                        check = task.getStatus() ? 'X' : ' ';
                        System.out.println("\t" + (i + 1) + "." + "[" + check + "] " + task.getDescription());
                    }
                    break;
                case "mark":
                    task = userRequests.get(Integer.parseInt(processedUserInput[1]) - 1);
                    if (task.getStatus()) {
                        System.out.println("\tUh oh! It looks like this task is already marked as done, please try again with another task!");
                    } else {
                        System.out.println("\tNice! I've marked this task as done:");
                        task.updateStatus();
                        check = task.getStatus() ? 'X' : ' ';
                        System.out.println("\t[" + check + "] " + task.getDescription());
                    }
                    break;
                case "unmark":
                    task = userRequests.get(Integer.parseInt(processedUserInput[1]) - 1);
                    if (!task.getStatus()) {
                        System.out.println("\tUh oh! It looks like this task is already marked as not done, please try again with another task!");
                    } else {
                        System.out.println("\tOK, I've marked this task as not done yet:");
                        task.updateStatus();
                        check = task.getStatus() ? 'X' : ' ';
                        System.out.println("\t[" + check + "] " + task.getDescription());
                    }
                    break;
                default:
                    System.out.println("\tadded: " + userInput);
                    // Create a new Task for each new item
                    Task newTask = new Task(userInput, false);
                    userRequests.add(newTask);
                    break;
            }
        } while (!Objects.equals(userInput, "bye"));

    }
}
