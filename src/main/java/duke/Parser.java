package duke;

import static duke.DukeException.BYE_ERROR;
import static duke.DukeException.DEADLINE_ERROR;
import static duke.DukeException.DELETE_ERROR;
import static duke.DukeException.EMPTY_ERROR;
import static duke.DukeException.EVENT_ERROR;
import static duke.DukeException.FIND_ERROR;
import static duke.DukeException.LIST_ERROR;
import static duke.DukeException.MARK_ERROR;
import static duke.DukeException.TAG_ERROR;
import static duke.DukeException.TODO_ERROR;
import static duke.DukeException.UNKNOWN_ERROR;
import static duke.DukeException.UNMARK_ERROR;

import duke.command.*;

/**
 * The `Parser` class represents a tool to make sense of user input.
 * It provides methods to verify and understand user input, then create respective commands.
 */
public class Parser {

    /**
     * Returns a command after parsing user input.
     * If the user input does not match any command, or any syntax error exists, throw exception.
     *
     * @param fullCommand User input.
     * @return A command object to be executed.
     * @throws IllegalArgumentException If the user input does not match any command, or any syntax error exists.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] splitTask = fullCommand.split(" ");
        TaskType taskType;

        // Empty command handler
        try {
            taskType = TaskType.valueOf(splitTask[0].toUpperCase());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(EMPTY_ERROR);
        } catch (IllegalArgumentException e) {
            throw new DukeException(UNKNOWN_ERROR);
        }

        switch (taskType) {
        case BYE: {
            return handleTaskBye(splitTask);
        }
        case LIST: {
            return handleTaskList(splitTask);
        }
        case MARK: {
            return handleTaskMark(splitTask);
        }
        case UNMARK: {
            return handleTaskUnmark(splitTask);
        }
        case TODO: {
            return handleTaskTodo(fullCommand, splitTask);
        }
        case DEADLINE: {
            return handleTaskDeadline(fullCommand, splitTask);
        }
        case EVENT: {
            return handleTaskEvent(fullCommand, splitTask);
        }
        case DELETE: {
            return handleTaskDelete(splitTask);
        }
        case FIND: {
            return handleTaskFind(fullCommand, splitTask);
        }
        case TAG: {
            return handleTaskTag(splitTask);
        }
        default: {
            throw new DukeException(UNKNOWN_ERROR);
        }
        }
    }

    /**
     * Returns a `ExitCommand` after parsing user input.
     * If any syntax error exists, throw exception.
     *
     * @param splitedTask Splited texts of the user input.
     * @return A command object to be executed.
     * @throws IllegalArgumentException If any syntax error exists.
     */
    public static Command handleTaskBye(String[] splitedTask) throws DukeException {
        // Incorrect command syntax handler
        if (splitedTask.length > 1) {
            throw new DukeException(BYE_ERROR);
        }
        return new ExitCommand();
    }

    /**
     * Returns a `ListCommand` after parsing user input.
     * If any syntax error exists, throw exception.
     *
     * @param splitedTask Splited texts of the user input.
     * @return A command object to be executed.
     * @throws IllegalArgumentException If any syntax error exists.
     */
    public static Command handleTaskList(String[] splitedTask) throws DukeException {
        // Incorrect command syntax handler
        if (splitedTask.length > 1) {
            throw new DukeException(LIST_ERROR);
        }
        return new ListCommand();
    }

    /**
     * Returns a `EditCommand` about mark after parsing user input.
     * If any syntax error exists, throw exception.
     *
     * @param splitedTask Splited texts of the user input.
     * @return A command object to be executed.
     * @throws IllegalArgumentException If any syntax error exists.
     */
    public static Command handleTaskMark(String[] splitedTask) throws DukeException {

        // Incorrect command syntax handler
        if (splitedTask.length != 2) {
            throw new DukeException(MARK_ERROR);
        }

        int index;
        try {
            index = Integer.parseInt(splitedTask[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(MARK_ERROR);
        }

        return new EditCommand(true, index);
    }

    /**
     * Returns a `EditCommand` about unmark after parsing user input.
     * If any syntax error exists, throw exception.
     *
     * @param splitedTask Splited texts of the user input.
     * @return A command object to be executed.
     * @throws IllegalArgumentException If any syntax error exists.
     */
    public static Command handleTaskUnmark(String[] splitedTask) throws DukeException {

        // Incorrect command syntax handler
        if (splitedTask.length != 2) {
            throw new DukeException(UNMARK_ERROR);
        }
        int index;
        try {
            index = Integer.parseInt(splitedTask[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(UNMARK_ERROR);
        }

        return new EditCommand(false, index);
    }

    /**
     * Returns a `AddCommand` about `Todo` after parsing user input.
     * If any syntax error exists, throw exception.
     *
     * @param task User input.
     * @param splitTask Splited texts of the user input.
     * @return A command object to be executed.
     * @throws IllegalArgumentException If any syntax error exists.
     */
    public static Command handleTaskTodo(String task, String[] splitTask) throws DukeException {

        // Incorrect command syntax handler
        if (splitTask.length == 1) {
            throw new DukeException(TODO_ERROR);
        }

        return new AddCommand(task);
    }

    /**
     * Returns a `AddCommand` about `Deadline` after parsing user input.
     * If any syntax error exists, throw exception.
     *
     * @param task User input.
     * @param splitedTask Splited texts of the user input.
     * @return A command object to be executed.
     * @throws IllegalArgumentException If any syntax error exists.
     */
    public static Command handleTaskDeadline(String task, String[] splitedTask) throws DukeException {

        // Incorrect syntax: No content, no deadline
        if (splitedTask.length == 1) {
            throw new DukeException(DEADLINE_ERROR);
        }

        // Remove the "deadline " keyword
        task = task.substring(9);

        // Split the string with /by
        String[] splitedBy = task.split(" /by ");

        // Incorrect syntax: The remaining string doesn't separate to content and deadline
        if (splitedBy.length != 2) {
            throw new DukeException(DEADLINE_ERROR);
        }

        // Get content and deadline
        String content = splitedBy[0].trim();
        String deadline = splitedBy[1].trim();

        // Verify content and deadline (cannot be blank)
        if (content.isBlank() || deadline.isBlank()) {
            throw new DukeException(DEADLINE_ERROR);
        }

        // Verify deadline (must be in format yyyy-MM-dd HH:mm or yyyy-mm-dd)
        String[] splitedDateTime = deadline.split(" ");
        if (splitedDateTime.length > 2) {
            throw new DukeException(DEADLINE_ERROR);
        }

        return new AddCommand(splitedDateTime, deadline, content);
    }

    /**
     * Returns a `AddCommand` about `Event` after parsing user input.
     * If any syntax error exists, throw exception.
     *
     * @param task User input.
     * @param splitedTask Splited texts of the user input.
     * @return A command object to be executed.
     * @throws IllegalArgumentException If any syntax error exists.
     */
    public static Command handleTaskEvent(String task, String[] splitedTask) throws DukeException {

        // Incorrect syntax: No content, no from, no to
        if (splitedTask.length == 1) {
            throw new DukeException(EVENT_ERROR);
        }

        // Remove the "event " keyword
        task = task.substring(6);

        // Split the string with /from
        String[] splitedFrom = task.split(" /from ");

        // Incorrect syntax: The remaining string doesn't separate to content, from and to
        if (splitedFrom.length != 2) {
            throw new DukeException(EVENT_ERROR);
        }

        // Get content, from and to
        String content = splitedFrom[0].trim();
        String fromTo = splitedFrom[1].trim();

        // Split the string with /to
        String[] splitedTo = fromTo.split(" /to ");

        // Incorrect syntax: The remaining string doesn't separate to from and to
        if (splitedTo.length != 2) {
            throw new DukeException(EVENT_ERROR);
        }

        // Get from and to
        String from = splitedTo[0].trim();
        String to = splitedTo[1].trim();

        // Verify content, from, and to (cannot be blank)
        if (content.isBlank() || from.isBlank() || to.isBlank()) {
            throw new DukeException(EVENT_ERROR);
        }

        // Verify from (must be in format yyyy-MM-dd HH:mm or yyyy-mm-dd)
        String[] splitedFromDateTime = from.split(" ");
        if (splitedFromDateTime.length > 2) {
            throw new DukeException(EVENT_ERROR);
        }

        // Verify to (must be in format yyyy-MM-dd HH:mm or yyyy-mm-dd)
        String[] splitedToDateTime = to.split(" ");
        if (splitedToDateTime.length > 2) {
            throw new DukeException(EVENT_ERROR);
        }

        return new AddCommand(splitedFromDateTime, splitedToDateTime, from, to, content);
    }

    /**
     * Returns a `DeleteCommand` after parsing user input.
     * If any syntax error exists, throw exception.
     *
     * @param splitTask Splited texts of the user input.
     * @return A command object to be executed.
     * @throws IllegalArgumentException If any syntax error exists.
     */
    public static Command handleTaskDelete(String[] splitTask) throws DukeException {

        // Incorrect command syntax handler
        if (splitTask.length != 2) {
            throw new DukeException(DELETE_ERROR);
        }
        int index;
        try {
            index = Integer.parseInt(splitTask[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(DELETE_ERROR);
        }

        return new DeleteCommand(index);
    }

    /**
     * Returns a `FindCommand` after parsing user input.
     * If any syntax error exists, throw exception.
     *
     * @param splitTask Splited texts of the user input.
     * @return A command object to be executed.
     * @throws IllegalArgumentException If any syntax error exists.
     */
    public static Command handleTaskFind(String task, String[] splitTask) throws DukeException {
        // Incorrect command syntax handler
        if (splitTask.length == 1) {
            throw new DukeException(FIND_ERROR);
        }

        return new FindCommand(task);
    }

    /**
     * Returns a `TagCommand` about tagging after parsing user input.
     * If any syntax error exists, throw exception.
     *
     * @param splitTask Splited texts of the user input.
     * @return A command object to be executed.
     * @throws IllegalArgumentException If any syntax error exists.
     */
    public static Command handleTaskTag(String[] splitTask) throws DukeException {

        // Incorrect command syntax handler
        if (splitTask.length != 3) {
            throw new DukeException(TAG_ERROR);
        }

        int index;
        try {
            index = Integer.parseInt(splitTask[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(TAG_ERROR);
        }

        return new TagCommand(index, splitTask[2]);
    }
}
