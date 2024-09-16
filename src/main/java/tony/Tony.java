package tony;

import java.util.Scanner;

import tony.exceptions.InvalidTaskException;
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
                    return handleListCommand();
                case "unmark":
                    return handleUnmarkCommand(input);
                case "mark":
                    return handleMarkCommand(input);
                case "todo":
                    return handleTodoCommand(input);
                case "deadline":
                    return handleDeadlineCommand(input);
                case "event":
                    return handleEventCommand(input);
                case "delete":
                    return handleDeleteCommand(input);
                case "find":
                    return handleFindCommand(input);
                case "update":
                    return handleUpdateCommand(input);
                case "hello":
                    return Ui.hello();
                default:
                    throw new IllegalArgumentException("Sorry dawg, that's an invalid command: " + command);
                }
            } catch (IllegalArgumentException e) {
                return "Error: " + e.getMessage();
            } catch (InvalidTaskException e) {
                return "Error creating task: " + e.getMessage();
            } catch (Exception e) {
                return "An unexpected error occurred: " + e.getMessage();
            }
        } else {
            storage.saveToFile(list.printTasksToString());
            return ui.goodbye();
        }
    }

    private String handleListCommand() {
        return list.print();
    }

    private String handleUnmarkCommand(String input) {
        String unmarkDescription = parser.parseDescription(input);
        return list.unmark(unmarkDescription);
    }

    private String handleMarkCommand(String input) {
        String markIndex = parser.parseDescription(input);
        return list.mark(markIndex);
    }

    private String handleTodoCommand(String input) throws InvalidTaskException {
        String todoDescription = parser.parseDescription(input);
        Task toDo = new TaskFactory().createTask(TaskType.TODO, todoDescription);
        return list.add(toDo);
    }

    private String handleDeadlineCommand(String input) throws InvalidTaskException {
        String[] deadlineParts = parser.parseTasksWithDate(input);
        Task deadline = new TaskFactory().createTask(TaskType.DEADLINE, deadlineParts);
        return list.add(deadline);
    }

    private String handleEventCommand(String input) throws InvalidTaskException {
        String[] eventParts = parser.parseTasksWithDate(input);
        Task event = new TaskFactory().createTask(TaskType.EVENT, eventParts);
        return list.add(event);
    }

    private String handleDeleteCommand(String input) {
        String deleteDescription = parser.parseDescription(input);
        return list.delete(deleteDescription);
    }

    private String handleFindCommand(String input) {
        String description = parser.parseDescription(input);
        return list.find(description);
    }

    private String handleUpdateCommand(String input) throws InvalidTaskException{
        String[] updateParts = parser.parseUpdate(input);
        return list.update(updateParts);
    }

}

