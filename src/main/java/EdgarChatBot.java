import java.util.Scanner;

public class EdgarChatBot {
    private TaskList taskList;

    public EdgarChatBot() {
        this.taskList = new TaskList();
    }
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
    public static void printGreetings() {
        printHorizontalLine();
        System.out.println("Hello! I'm Edgar.");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }
    public static void printByeMessage() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public void start() {
        printGreetings();
        Scanner scanner = new Scanner(System.in);
        String userInput;

    try {
        while (true) {
            userInput = scanner.nextLine();
            String[] splitInput = userInput.split(" ", 2);
            String action = splitInput[0];

            if (userInput.equalsIgnoreCase("bye")) {
                printByeMessage();
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                try {
                    this.taskList.listTasks();
                } catch (ChatBotException e) {
                    System.out.println(e.getMessage());
                }
                this.taskList.listTasks();
            }

            if (action.equalsIgnoreCase("mark")) {
                try {
                    this.taskList.markTask(Integer.parseInt(splitInput[1]));
                } catch (ChatBotException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }

            if (action.equalsIgnoreCase("unmark")) {
                try {
                    this.taskList.unmarkTask(Integer.parseInt(splitInput[1]));
                } catch (ChatBotException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }

            if (action.equalsIgnoreCase("delete")) {
                try {
                    this.taskList.deleteTask(Integer.parseInt(splitInput[1]));
                } catch (ChatBotException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }

            this.taskList.addTask(userInput);
        }
    } catch (ChatBotException e) {
        System.out.println(e.getMessage());
    }

        scanner.close();
    }
}

