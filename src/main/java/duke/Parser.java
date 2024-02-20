package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;

/**
 * Parses user commands and manages the execution of corresponding tasks.
 */
public class Parser {
    /**
     * Represents the possible requests that can be parsed from user commands.
     */
    public enum Request {
        BYE, LIST, MARK, TODO, DEADLINE, EVENT, DELETE, FIND, INVALID
    }

    /**
     * Parses the user command and executes the corresponding action based on the request.
     *
     * @param userInput The user's input command.
     * @return A string message indicating the result of the command execution.
     */
    public Command parseCommand(String userInput) throws DukeException, NumberFormatException,
            ArrayIndexOutOfBoundsException {
        Request request = getRequest(userInput);
        int index;
        Task task;

        switch (request) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            index = Integer.parseInt(userInput.substring("mark".length()).trim());
            return new MarkCommand(index);
        case TODO:
            task = parseTodo(userInput);
            return new AddCommand(task);
        case DEADLINE:
            task = parseDeadline(userInput);
            return new AddCommand(task);
        case EVENT:
            task = parseEvent(userInput);
            return new AddCommand(task);
        case DELETE:
            index = Integer.parseInt(userInput.substring("delete".length()).trim());
            return new DeleteCommand(index);
        case FIND:
            String keyword = userInput.substring("find".length()).trim();
            return new FindCommand(keyword);
        case INVALID:
            return new InvalidCommand();
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Determines the request type based on the user's input command.
     *
     * @param userInput The user's input command.
     * @return The Request enum corresponding to the parsed command.
     */
    public Request getRequest(String userInput) {
        String inputUpper = userInput.toUpperCase();

        for (Request request : Request.values()) {
            if (inputUpper.startsWith(request.name())) {
                return request;
            }
        }

        return Request.INVALID;
    }

    private Task parseTodo(String userInput) throws DukeException {
        String desc = userInput.substring("todo".length()).trim();
        if (desc.isEmpty()) {
            throw new DukeException("Task description cannot be empty.");
        }
        return new Todo(desc);
    }

    private Task parseDeadline(String userInput) throws DukeException {
        String desc = userInput.substring("deadline".length()).trim();
        String[] taskAndDeadline = desc.split("/by");

        if (taskAndDeadline.length > 2) {
            throw new DukeException("Please enter format deadline (task) /by (yyyy-mm-dd hhmm)");
        } else if (taskAndDeadline[1].trim().isEmpty()) {
            throw new DukeException("Empty timing. Please enter format deadline (task) /by (yyyy-mm-dd hhmm)");
        }

        String taskString = taskAndDeadline[0].trim();
        String byString = taskAndDeadline[1].trim();
        String dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

        if (Pattern.matches(dateTimePattern, byString)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime dateTimeBy = LocalDateTime.parse(byString, formatter);
            return new Deadline(taskString, dateTimeBy);
        } else {
            throw new DukeException("Please enter date in the format (yyyy-mm-dd hhmm)");
        }
    }

    private Task parseEvent(String userInput) throws DukeException {
        String desc = userInput.substring("event".length()).trim();
        String[] taskFromAndTo = desc.split("/from");

        if (taskFromAndTo.length > 2) {
            throw new DukeException("Please enter format event (task) /from (yyyy-mm-dd hhmm) "
                    + "/to (yyyy-mm-dd hhmm)");
        } else if (taskFromAndTo[1].trim().isEmpty()) {
            throw new DukeException("Empty timing. Please enter format event (task) /from "
                    + "(yyyy-mm-dd hhmm) /to (yyyy-mm-dd hhmm)");
        }

        String[] fromAndTo = taskFromAndTo[1].split("/to");

        if (fromAndTo.length > 2) {
            throw new DukeException("Please enter format event (task) /from (yyyy-mm-dd hhmm)"
                    + " /to (yyyy-mm-dd hhmm)");
        } else if (fromAndTo[1].trim().isEmpty()) {
            throw new DukeException("Empty timing. Please enter format event (task) /from "
                    + "(yyyy-mm-dd hhmm) /to (yyyy-mm-dd hhmm)");
        }

        String taskString = taskFromAndTo[0].trim();
        String fromString = fromAndTo[0].trim();
        String toString = fromAndTo[1].trim();
        String dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

        if (Pattern.matches(dateTimePattern, fromString) && Pattern.matches(dateTimePattern, toString)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime dateTimeFrom = LocalDateTime.parse(fromString, formatter);
            LocalDateTime dateTimeTo = LocalDateTime.parse(toString, formatter);
            return new Event(taskString, dateTimeFrom, dateTimeTo);
        } else {
            throw new DukeException("Please enter date in the format (yyyy-mm-dd hhmm)");
        }
    }
}
