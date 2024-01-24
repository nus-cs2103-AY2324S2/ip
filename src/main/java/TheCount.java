import java.util.Scanner;

public class TheCount {
    public static void main(String[] args) {
        // Creates a Scanner object to read input
        Scanner scanner = new Scanner(System.in);

        // Creates a list of tasks
        TaskList tasks = new TaskList();

        // Creates standard replies
        Reply greeting = new Greeting();
        Reply goodbye = new Goodbye();

        // Prints greeting
        greeting.displayMessage();

        // Wait for user input
        String userInput = scanner.nextLine();
        // Get first word for CommandType
        String commandName = userInput.split("\\s+")[0].toUpperCase();
        int taskNumber = 0;
        // Checks for BYE command
        while (!commandName.equals("BYE")) {
            // Checks for switch case conditions
            commandName = userInput.split("\\s+")[0].toUpperCase();
            switch (commandName) {
                case "BYE":
                    // Prints goodbye for exit
                    goodbye.displayMessage();
                    scanner.close();
                    System.exit(0);
                    break;
                case "LIST":
                    tasks.printList();
                    break;
                case "MARK":
                    taskNumber = Integer.parseInt(userInput.split("\\s+")[1]);
                    tasks.markTask(taskNumber);
                    break;
                case "UNMARK":
                    taskNumber = Integer.parseInt(userInput.split("\\s+")[1]);
                    tasks.unmarkTask(taskNumber);
                    break;
                default:
                    Task task = new Task(userInput);
                    tasks.add(task);
                    task.displayMessage();
                    break;
            }
            userInput = scanner.nextLine();
        }
        scanner.close();
    }
}
