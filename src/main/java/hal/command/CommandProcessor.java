package hal.command;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import hal.codec.TimeProcessor;
import hal.exceptions.InputException;
import hal.exceptions.ProcessingException;
import hal.history.HistoryManager;
import hal.history.State;
import hal.storage.Storage;
import hal.tasks.Deadline;
import hal.tasks.Event;
import hal.tasks.Task;
import hal.tasks.Todo;

/**
 * The `CommandProcessor` class handles the processing of user commands in the Duke application.
 * It interprets user input, executes commands, and interacts with the storage system to manage tasks.
 */
public class CommandProcessor {
    private final Storage storage;
    private final HistoryManager historyManager;

    /**
     * Constructs a new CommandProcessor object with the specified storage and historyManager.
     *
     * @param storage        The storage system for managing tasks.
     * @param historyManager The history manager for maintaining command history.
     */
    public CommandProcessor(Storage storage, HistoryManager historyManager) {
        this.storage = storage;
        this.historyManager = historyManager;
    }

    /**
     * Processes the given command and input, executing the corresponding action.
     * An assertion will check if the command to execute falls through the switch case.
     *
     * @param command The command to execute.
     * @param input   The user input associated with the command.
     * @throws InputException      If there is an issue with the input format or command.
     * @throws ProcessingException If there is an issue processing the command or storage.
     */
    public String processData(Command command, String input) throws InputException, ProcessingException {
        String result = "";
        switch (command) {

        case DELETE:
            result = storage.delete(processDelete(input));
            break;

        case LIST:
            result = storage.displayList();
            break;

        case MARK:
            result = storage.mark(processMark(input, Command.MARK));
            break;

        case UNMARK:
            result = storage.unmark(processMark(input, Command.UNMARK));
            break;

        case DEADLINE:
            result = storage.add(processDeadline(input));
            break;

        case EVENT:
            result = storage.add(processEvent(input));
            break;

        case TODO:
            result = storage.add(processTodo(input));
            break;

        case FIND:
            result = storage.displaySearchList(processFind(input));
            break;

        case UNDO:
            result = historyManager.undo(storage);
            break;

        case REDO:
            result = historyManager.redo(storage);
            break;

        default:
            assert false : command;
            break;
        }

        State currState = storage.update(command);
        historyManager.updateHistory(currState);
        return result;
    }
    /**
     * Processes the DELETE command input to extract the task index to be deleted.
     *
     * @param input The user input for the DELETE command.
     * @return The index of the task to be deleted.
     * @throws InputException If there is an issue with the input format or command.
     */
    public Integer processDelete(String input) throws InputException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw InputException.exceptionCommandParsing(Command.DELETE, input, e);
        }
    }

    /**
     * Processes the DEADLINE command input to create a `Deadline` task.
     *
     * @param input The user input for the DEADLINE command.
     * @return A `Deadline` task created from the input.
     * @throws InputException If there is an issue with the input format or command.
     */
    public Task processDeadline(String input) throws InputException {
        try {
            String restOfInput = input.substring(9);
            String[] splitInput = restOfInput.split(" /by ");

            String taskName = splitInput[0];
            LocalDateTime by = TimeProcessor.fromString(splitInput[1]);

            return new Deadline(taskName, by);

        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw InputException.exceptionCommandParsing(Command.DEADLINE, input, e);
        }
    }

    /**
     * Processes the TODO command input to create a `Todo` task.
     *
     * @param input The user input for the TODO command.
     * @return A `Todo` task created from the input.
     * @throws InputException If there is an issue with the input format or command.
     */
    public Task processTodo(String input) throws InputException {
        try {
            String taskName = input.substring(5);
            return new Todo(taskName);
        } catch (IndexOutOfBoundsException e) {
            throw InputException.exceptionCommandParsing(Command.TODO, input, e);
        }
    }

    /**
     * Processes the EVENT command input to create an `Event` task.
     *
     * @param input The user input for the EVENT command.
     * @return An `Event` task created from the input.
     * @throws InputException If there is an issue with the input format or command.
     */
    public Task processEvent(String input) throws InputException {
        try {
            String restOfInput = input.substring(6);
            String[] splitFrom = restOfInput.split(" /from ");
            String[] fromTo = splitFrom[1].split(" /to ");
            String taskName = splitFrom[0];
            LocalDateTime from = TimeProcessor.fromString(fromTo[0]);
            LocalDateTime to = TimeProcessor.fromString(fromTo[1]);
            return new Event(taskName, from, to);

        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw InputException.exceptionCommandParsing(Command.EVENT, input, e);
        }
    }

    /**
     * Processes the MARK or UNMARK command input to extract the task index to be marked or unmarked.
     *
     * @param input The user input for the MARK or UNMARK command.
     * @param cmd   The command type (MARK or UNMARK).
     * @return The index of the task to be marked or unmarked.
     * @throws InputException If there is an issue with the input format or command.
     */
    public Integer processMark(String input, Command cmd) throws InputException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw InputException.exceptionCommandParsing(cmd, input, e);
        }
    }

    /**
     * Processes the FIND command input to extract the search query.
     *
     * @param input The user input for the FIND command.
     * @return The search query to find matching tasks.
     * @throws InputException If there is an issue with the input format or command.
     */
    public String processFind(String input) throws InputException {
        try {
            return input.split(" ")[1];

        } catch (IndexOutOfBoundsException e) {
            throw InputException.exceptionCommandParsing(Command.FIND, input, e);
        }
    }

    public void close() {
        storage.close();
    }
}
