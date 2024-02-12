package tony;

import java.util.Scanner;

import tony.tasks.Task;
import tony.tasks.TaskType;


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
     */
    public Tony() {
        ui = new Ui();
        storage = new Storage("data.txt");
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
    public String run(String input) {
        if (!input.equals("bye")) {
            String command = parser.parseCommand(input);
            try {
                switch (command) {
                    case "list":
                        return list.print();
                    case "unmark":
                        String unmarkDescription = parser.parseDescription(input);
                        return list.unmark(unmarkDescription);
                    case "mark":
                        String markIndex = parser.parseDescription(input);
                        return list.mark(markIndex);
                    case "todo":
                        String todoDescription = parser.parseDescription(input);
                        Task toDo = new TaskFactory().createTask(TaskType.TODO, todoDescription);
                        return list.add(toDo);
                    case "deadline":
                        String[] deadlineParts = parser.parseTasksWithDate(input);
                        Task deadline = new TaskFactory().createTask(TaskType.DEADLINE, deadlineParts);
                        return list.add(deadline);
                    case "event":
                        String[] eventParts = parser.parseTasksWithDate(input);
                        Task event = new TaskFactory().createTask(TaskType.EVENT, eventParts);
                        return list.add(event);
                    case "delete":
                        String deleteDescription = parser.parseDescription(input);
                        return list.delete(deleteDescription);
                    case "find":
                        String description = parser.parseDescription(input);
                        return list.find(description);
                    default:
                        throw new IllegalArgumentException("Invalid command: " + command);
                }
            } catch (IllegalArgumentException e) {
                return "Error: " + e.getMessage();
            } catch (Exception e) {
                return "An unexpected error occurred: " + e.getMessage();
            }
        } else {
            storage.saveToFile(list.printTasksToString());
            return ui.goodbye();
        }
    }
}

