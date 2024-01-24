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
        String info = null;
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
                case "TODO":
                    info = userInput.split("\\s+", 2)[1];
                    ToDo todo = new ToDo(info);
                    tasks.add(todo);
                    todo.displayMessage(tasks.length());
                    break;
                case "DEADLINE":
                    info = userInput.split("\\s+", 2)[1].split("/by")[0].trim();
                    String deadlineTime = userInput.split("/by")[1].trim();
                    Deadline deadline = new Deadline(info, deadlineTime);
                    tasks.add(deadline);
                    deadline.displayMessage(tasks.length());
                    break;
                case "EVENT":
                    info = userInput.split("\\s+", 2)[1].split("/from")[0].trim();
                    String startTime = userInput.split("/from")[1].trim()
                            .split("/to")[0].trim();
                    String endTime = userInput.split("/to")[1].trim();
                    Event event = new Event(info, startTime, endTime);
                    tasks.add(event);
                    event.displayMessage(tasks.length());
                    break;
                default:
                    break;
            }
            userInput = scanner.nextLine();
        }
        scanner.close();
    }
}
