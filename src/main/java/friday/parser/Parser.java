package friday.parser;

import java.io.IOException;

import friday.storage.Storage;
import friday.task.TaskList;

/**
 * The Parser class is responsible for interpreting user input commands and executing corresponding actions.
 * It interacts with the TaskList and Storage classes to manage tasks and data storage.
 */
public class Parser {
    private TaskList tasks;

    private Storage storage;

    /**
     * Constructs a Parser object with the specified TaskList and file path for data storage.
     * @param tasks The TaskList instance to manage tasks.
     * @param filePath The file path for data storage.
     */
    public Parser(TaskList tasks, String filePath) {
        assert tasks != null : "TaskList must not be null";
        assert filePath != null : "File path must not be null";
        this.tasks = tasks;
        this.storage = new Storage(filePath);
    }

    /**
     * Parses the user input command and executes the corresponding action.
     * @param userInput The user input command to be parsed.
     * @return A String representing the response to the user command.
     */
    public String parseInput(String userInput) {
        assert userInput != null : "User input must not be null";
        String category = userInput.split(" ")[0].toLowerCase().trim();
        switch (category) {
        case "list":
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list:");
            sb.append(System.lineSeparator());
            for (int i = 0; i < tasks.getLength(); i++) {
                sb.append(i + 1 + ". " + tasks.getTask(i).toString());
                sb.append(System.lineSeparator());
            }
            return sb.toString();
        case "mark":
            assert tasks != null : "TaskList must not be null";
            assert storage != null : "Storage must not be null";
            String responseToMark = tasks.markTask(userInput);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                return e.getMessage();
            }
            return responseToMark;
        case "unmark":
            assert tasks != null : "TaskList must not be null";
            assert storage != null : "Storage must not be null";
            String responseToUnmark = tasks.unmarkTask(userInput);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                return e.getMessage();
            }
            return responseToUnmark;
        case "todo":
            return tasks.addTodo(userInput);
        case "deadline":
            return tasks.addDeadline(userInput);
        case "event":
            return tasks.addEvent(userInput);
        case "delete":
            assert tasks != null : "TaskList must not be null";
            assert storage != null : "Storage must not be null";
            String responseToDelete = tasks.deleteTask(userInput);
            try {
                storage.writeToFile(tasks);
            } catch (IOException err) {
                return err.getMessage();
            }
            return responseToDelete;
        case "find":
            return tasks.searchTask(userInput);
        case "bye":
            return "Bye Master. Have a nice day!";
        default:
            return "HUH? What do you mean?";
        }
    }
}
