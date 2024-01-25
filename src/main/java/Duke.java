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
                handleCommand(input, userMessage, taskManager);
            } else {
                goodbye.printDialogue("goodbye1");
                break;
            }
        }

        scanner.close();
    }

    private static void handleCommand(String input, String[] userMessage, TaskManager taskManager) {
        switch (userMessage[0].toLowerCase()) {
            case "list":
                taskManager.displayTask(input);
                break;
            case "mark":
                taskManager.markAsComplete(Integer.parseInt(userMessage[1]) - 1);
                taskManager.displayTask(input);
                break;
            case "unmark":
                taskManager.markAsIncomplete(Integer.parseInt(userMessage[1]) - 1);
                taskManager.displayTask(input);
                break;
            case "todo":
                taskManager.addTask(input, TaskType.TODO);
                taskManager.displayTask(input);
                break;
            case "deadline":
                taskManager.addTask(input, TaskType.DEADLINE);
                taskManager.displayTask(input);
                break;
            case "event":
                taskManager.addTask(input, TaskType.EVENT);
                taskManager.displayTask(input);
                break;
            default:
                taskManager.addTask(input, TaskType.NORMAL);
                taskManager.displayTask(input);
                break;
        }
    }
}