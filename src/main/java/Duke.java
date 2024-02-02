import task.TaskManager;
import task.TaskType;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initializing Conversation
        Conversation conversation = new Conversation();

        conversation.printDialogue("default");
        conversation.printDialogue("username");

        String input = scanner.nextLine();
        if (!input.isBlank()) {
            Conversation.userName = input.toUpperCase();
        }
        conversation.addDialogue("starter", "Hello, " + Conversation.userName + ". Nice to meet you!\n" + Conversation.INDENTATION + "So, what can I do for you today?");
        conversation.printDialogue("starter");

        // Initializing TaskManager
        TaskManager taskManager = new TaskManager(Conversation.userName);

        while (true) {
            input = scanner.nextLine();
            String[] userMessage = input.split(" ");

            if (!userMessage[0].equalsIgnoreCase("bye")) {
                handleCommand(input, userMessage, taskManager, conversation);
            } else {
                conversation.printDialogue("bye");
                taskManager.autoSaveTask();
                break;
            }
        }

        scanner.close();
    }

    private static void handleCommand(String input, String[] userMessage, TaskManager taskManager, Conversation conversation) {
        if (input.equalsIgnoreCase("delete all")) {
            taskManager.deleteAllTasks();
            return;
        }

        switch (userMessage[0].toLowerCase()) {
            case "list":
                taskManager.displayTask(input);
                break;
            case "mark":
                if (userMessage.length == 1 || isNumeric(userMessage[1])) {
                    printError(input);
                    break;
                }
                taskManager.markAsComplete(Integer.parseInt(userMessage[1]) - 1);
                taskManager.displayTask(input);
                break;
            case "unmark":
                if (userMessage.length == 1 || isNumeric(userMessage[1])) {
                    printError(input);
                    break;
                }
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
            case "delete":
                if (userMessage.length == 1 || isNumeric(userMessage[1])) {
                    printError(input);
                    break;
                }
                taskManager.displayTask(input);
                taskManager.deleteTask(Integer.parseInt(userMessage[1]) - 1);
                break;
            default:
                conversation.printDialogue(input);
                break;
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private static void printError(String input) {
        System.out.println(TaskManager.LINE);
        System.out.println(TaskManager.INDENTATION + "Sorry " + Conversation.userName + ", the TASK NUMBER is missing after " + input.toLowerCase() + ".");
        System.out.println(TaskManager.INDENTATION + "Can you please specify a valid task number from the list?");
        System.out.println(TaskManager.LINE);
    }
}