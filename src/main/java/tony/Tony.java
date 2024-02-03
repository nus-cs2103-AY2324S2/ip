package tony;

import tony.tasks.Task;
import tony.tasks.TaskType;
import java.util.Scanner;

/**
 * The main class representing the Tony application.
 */
public class Tony {
    private Ui ui;
    private TodoList list;
    private Storage storage;
    private Scanner scanner;
    private Parser parser;

    /**
     * Initializes a new instance of the Tony application.
     *
     * @param filePath The path to the file used for storing task data.
     */
    public Tony(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        scanner = new Scanner(System.in);
        parser = new Parser();
        try {
            list = storage.load();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            list = new TodoList();
        }

    }
    /**
     * Runs the Tony application, allowing users to interact with tasks.
     */
    public void run() {
        ui.greeting();
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            String command = parser.parseCommand(input);
            try {
                switch (command) {
                    case "list":
                        list.print();
                        break;
                    case "unmark":
                        String unmarkDescription = parser.parseDescription(input);
                        list.unmark(unmarkDescription);
                        break;
                    case "mark":
                        String markIndex = parser.parseDescription(input);
                        list.mark(markIndex);
                        break;
                    case "todo":
                        String todoDescription = parser.parseDescription(input);
                        Task toDo = new TaskFactory().createTask(TaskType.TODO, todoDescription);
                        list.add(toDo);
                        break;
                    case "deadline":
                        String[] deadlineParts = parser.parseTasksWithDate(input);
                        Task deadline = new TaskFactory().createTask(TaskType.DEADLINE, deadlineParts);
                        list.add(deadline);
                        break;
                    case "event":
                        String[] eventParts = parser.parseTasksWithDate(input);
                        Task event = new TaskFactory().createTask(TaskType.EVENT, eventParts);
                        list.add(event);
                        break;
                    case "delete":
                        String deleteDescription = parser.parseDescription(input);
                        list.delete(deleteDescription);
                        break;
                    case "find":
                        String description = parser.parseDescription(input);
                        list.find(description);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid command: " + command);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
            input = scanner.nextLine();
        }
        storage.saveToFile(list.printTasksToString());
        ui.goodbye();
    }
    /**
     * The main entry point of the Tony application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Tony("data.txt").run();
    }

}

