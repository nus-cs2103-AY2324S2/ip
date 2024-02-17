package denify.core;

import denify.exception.DenifyException;
import denify.parser.Parser;
import denify.storage.Storage;
import denify.task.Task;
import denify.task.TaskList;
import denify.ui.Ui;

/**
 * Responsible for parsing and executing commands in the Denify application,
 * encapsulating the logic for handling different types of commands.
 */
public class CommandHandler {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    /**
     * Constructs a `CommandHandler` with the specified dependencies.
     *
     * @param tasks   The list of tasks managed by Denify.
     * @param storage The storage component responsible for loading and saving tasks.
     * @param ui      The user interface component for interacting with the user.
     */
    public CommandHandler(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }
    /**
     * Executes the specified command by parsing it and performing the corresponding action.
     *
     * @param command The command to be executed.
     * @param parser  The parser instance used for parsing the command.
     * @return A string representing the response to the executed command.
     */
    public String execute(Command command, Parser parser) {
        StringBuilder response = new StringBuilder();

        try {
            switch (command) {
            case BYE:
                response.append(handleBye(parser));
                break;
            case LIST:
                response.append(handleList(parser));
                break;
            case FIND:
                response.append(handleFind(parser));
                break;
            case MARK:
                response.append(handleMark(parser));
                break;
            case UNMARK:
                response.append(handleUnmark(parser));
                break;
            case DELETE:
                response.append(handleDelete(parser));
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                response.append(handleTask(command, parser));
                break;
            default:
                response.append("Unable to understand the command. Please enter a valid command.");
                break;
            }
        } catch (DenifyException e) {
            response.append(e.getMessage());
        }
        return response.toString();
    }
    /**
     * Handles the "bye" command.
     *
     * @param parser The parser instance used for parsing the command.
     * @return A string representing the response to the "bye" command.
     * @throws DenifyException If there is an issue parsing the command.
     */
    private String handleBye(Parser parser) throws DenifyException {
        parser.parseBye();
        return ui.exit();
    }

    /**
     * Handles the "list" command.
     *
     * @param parser The parser instance used for parsing the command.
     * @return A string representing the response to the "list" command.
     * @throws DenifyException If there is an issue parsing the command.
     */
    private String handleList(Parser parser) throws DenifyException {
        parser.parseList();
        return ui.showAllTasks(tasks.getTasks());
    }

    /**
     * Handles the "find" command.
     *
     * @param parser The parser instance used for parsing the command.
     * @return A string representing the response to the "find" command.
     * @throws DenifyException If there is an issue parsing the command.
     */
    private String handleFind(Parser parser) throws DenifyException {
        String description = parser.parseFind();
        return ui.showFoundTasks(tasks.findTasks(description));
    }

    /**
     * Handles the "mark" command.
     *
     * @param parser The parser instance used for parsing the command.
     * @return A string representing the response to the "mark" command.
     * @throws DenifyException If there is an issue parsing the command.
     */
    private String handleMark(Parser parser) throws DenifyException {
        int markIndex = parser.parseMark();
        Task markTask = tasks.markTask(markIndex);
        tasks.saveToStorage(storage);
        return ui.showMarkTaskMessage(markTask);
    }

    /**
     * Handles the "unmark" command.
     *
     * @param parser The parser instance used for parsing the command.
     * @return A string representing the response to the "unmark" command.
     * @throws DenifyException If there is an issue parsing the command.
     */
    private String handleUnmark(Parser parser) throws DenifyException {
        int unmarkIndex = parser.parseUnmark();
        Task unmarkTask = tasks.unmarkTask(unmarkIndex);
        tasks.saveToStorage(storage);
        return ui.showUnmarkTaskMessage(unmarkTask);
    }

    /**
     * Handles the "delete" command.
     *
     * @param parser The parser instance used for parsing the command.
     * @return A string representing the response to the "delete" command.
     * @throws DenifyException If there is an issue parsing the command.
     */
    private String handleDelete(Parser parser) throws DenifyException {
        int deleteIndex = parser.parseDelete();
        Task deleteTask = tasks.deleteTask(deleteIndex);
        tasks.saveToStorage(storage);
        return ui.showDeleteTaskMessage(deleteTask, tasks.getTasks().size());
    }
    /**
     * Handles the "todo", "deadline", and "event" commands.
     *
     * @param command The command to be handled.
     * @param parser  The parser instance used for parsing the command.
     * @return A string representing the response to the task-related command.
     * @throws DenifyException If there is an issue parsing the command.
     */
    private String handleTask(Command command, Parser parser) throws DenifyException {
        Task task;
        switch (command) {
        case TODO:
            task = parser.parseTodo();
            break;
        case DEADLINE:
            task = parser.parseDeadline();
            break;
        case EVENT:
            task = parser.parseEvent();
            break;
        default:
            throw new DenifyException("Unsupported task command: " + command.name());
        }
        tasks.addTask(task);
        tasks.saveToStorage(storage);
        return ui.showAddTaskMessage(task, tasks.getTasks().size());
    }
}
