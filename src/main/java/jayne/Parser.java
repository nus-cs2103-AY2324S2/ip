package jayne;

import jayne.task.TaskList;

/**
 * This class is responsible for parsing user input and executing the corresponding commands.
 * It serves as an intermediary between the user interface and the task handler.
 */
public class Parser {
    private static final String ERROR_MESSAGE = "What are you typing. please include either bye, list, mark, "
            + "umark, todo, deadline or event in your inputs please";
    private TaskList taskList;

    /**
     * Constructs a new Parser.
     *
     * @param taskList the TaskList to be manipulated based on user commands.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the user input and executes the corresponding command.
     *
     * @param input the raw input string from the user.
     * @return true if the command is a termination command (e.g., 'bye'), otherwise false.
     * @throws JayneException if the command is invalid or if any issues occur during command execution.
     */
    public String parse(String input) throws JayneException {
        assert input != null : "Input cannot be null";
        String[] parts = input.split(" ", 2);
        String commandText = parts[0].toLowerCase();
        switch (commandText) {
        case "sort":
            return Handler.handleSort(parts, taskList);
        case "find":
            return Handler.handleFind(parts, taskList);
        case "bye":
            return Handler.handleBye();
        case "list":
            return Handler.handleList(taskList);
        case "mark":
            return Handler.handleMark(parts, taskList);
        case "unmark":
            return Handler.handleUnmark(parts, taskList);
        case "todo":
            return Handler.handleTodo(parts, taskList);
        case "deadline":
            return Handler.handleDeadline(parts, taskList);
        case "event":
            return Handler.handleEvent(parts, taskList);
        case "delete":
            return Handler.handleDelete(parts, taskList);
        default:
            return ERROR_MESSAGE;
        }
    }
}
