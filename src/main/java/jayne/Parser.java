package jayne;

import jayne.task.TaskList;

/**
 * This class is responsible for parsing user input and executing the corresponding commands.
 * It serves as an intermediary between the user interface and the task handler.
 */
public class Parser {
    //private String[] parts;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a new Parser.
     *
     * @param taskList the TaskList to be manipulated based on user commands.
     * @param ui       the Ui responsible for interactions with the user.
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Parses the user input and executes the corresponding command.
     *
     * @param input the raw input string from the user.
     * @return true if the command is a termination command (e.g., 'bye'), otherwise false.
     * @throws JayneException if the command is invalid or if any issues occur during command execution.
     */
    public boolean parse(String input) throws JayneException {
        String[] parts = input.split(" ", 2);
        String commandText = parts[0].toLowerCase();
        switch (commandText) {
        case "bye":
            Handler.handleBye();
            return true;
        case "list":
            Handler.handleList(taskList);
            break;
        case "mark":
            Handler.handleMark(parts, taskList);
            break;
        case "unmark":
            Handler.handleUnmark(parts, taskList);
            break;
        case "todo":
            Handler.handleTodo(parts, taskList);
            break;
        case "deadline":
            Handler.handleDeadline(parts, taskList);
            break;
        case "event":
            Handler.handleEvent(parts, taskList);
            break;
        case "delete":
            Handler.handleDelete(parts, taskList);
            break;
        default:
            throw new JayneException(ui.question());
        }
        return false;
    }
}
