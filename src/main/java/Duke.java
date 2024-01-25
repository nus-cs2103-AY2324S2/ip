import java.util.Scanner;
import java.util.StringTokenizer;
public class Duke {
    public static final String CHATBOTNAME = "Sophia";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialization of common commands
        Greetings greetings = new Greetings();
        Goodbye goodbye = new Goodbye();
        TaskManager taskManager = new TaskManager();

        greetings.printDialogue("greeting2");

        while (true) {
            String input = scanner.nextLine();
            String[] userMessage = input.split(" ");

            if (!userMessage[0].equalsIgnoreCase("bye")) {
                if (userMessage[0].equalsIgnoreCase("list")) {
                    taskManager.displayTask(input);
                    continue;
                } else if (userMessage[0].equalsIgnoreCase("mark")) {
                    taskManager.markAsComplete(Integer.parseInt(userMessage[1]) - 1);
                    taskManager.displayTask(input);
                } else if (userMessage[0].equalsIgnoreCase("unmark")) {
                    taskManager.markAsIncomplete(Integer.parseInt(userMessage[1]) - 1);
                    taskManager.displayTask(input);
                }
                else {
                    taskManager.addTask(input);
                    taskManager.displayTask(input);
                }
            } else {
                goodbye.printDialogue("goodbye1");
                break;
            }
        }

        scanner.close();

    }
}

