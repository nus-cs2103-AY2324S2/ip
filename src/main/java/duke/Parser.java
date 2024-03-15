package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.RecurCommand;

/**
 * Parses user commands and manages the execution of corresponding tasks.
 */
public class Parser {
    /**
     * Represents the possible requests that can be parsed from user commands.
     */
    public enum Request {
        BYE, LIST, MARK, TODO, DEADLINE, EVENT, DELETE, FIND, INVALID, RECUR
    }

    /**
     * Parses the user command and executes the corresponding action based on the request.
     *
     * @param userInput The user's input command.
     * @return A string message indicating the result of the command execution.
     */
    public Command parseCommand(String userInput) throws DukeException, NumberFormatException,
            ArrayIndexOutOfBoundsException {
        String[] inputs = userInput.split(" ", 2);
        Request request = getRequest(inputs[0]);
        int index;
        Task task;

        switch (request) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            index = Integer.parseInt(inputs[1]);
            return new MarkCommand(index);
        case TODO:
            task = parseTodo(inputs[1]);
            return new AddCommand(task);
        case DEADLINE:
            task = parseDeadline(inputs[1]);
            return new AddCommand(task);
        case EVENT:
            task = parseEvent(inputs[1]);
            return new AddCommand(task);
        case DELETE:
            index = Integer.parseInt(inputs[1]);
            return new DeleteCommand(index);
        case FIND:
            String keyword = inputs[1];
            return new FindCommand(keyword);
        case RECUR:
            index = Integer.parseInt(inputs[1]);
            return new RecurCommand(index);
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
            if (inputUpper.equals(request.name())) {
                return request;
            }
        }

        return Request.INVALID;
    }

    private Task parseTodo(String userInput) throws DukeException {
        if (userInput.isEmpty()) {
            throw new DukeException("Task description cannot be empty.");
        }
        return new Todo(userInput);
    }

    private Task parseDeadline(String userInput) throws DukeException {
        String[] taskAndDeadline = userInput.trim().split("/by");

        if (taskAndDeadline.length > 2) {
            throw new DukeException("Please enter format deadline (task) /by (yyyy-mm-dd hhmm)");
        } else if (taskAndDeadline[0].trim().isEmpty()) {
            throw new DukeException("Empty task description. Please enter format deadline (task)"
                    + " /by (yyyy-mm-dd hhmm)");
        } else if (taskAndDeadline.length == 1) {
            throw new DukeException("Empty timing. Please enter format deadline (task) /by (yyyy-mm-dd hhmm)");
        }

        String taskString = taskAndDeadline[0].trim();
        String byString = taskAndDeadline[1].trim();
        String dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

        if (Pattern.matches(dateTimePattern, byString)) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm")
                        .withResolverStyle(ResolverStyle.STRICT);
                LocalDateTime dateTimeBy = LocalDateTime.parse(byString, formatter);
                return new Deadline(taskString, dateTimeBy);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date or time. Please enter date and time in the"
                        + " format:yyyy-mm-dd hhmm");
            }
        } else {
            throw new DukeException("Please enter date in the format (yyyy-mm-dd hhmm)");
        }
    }

    private Task parseEvent(String userInput) throws DukeException {
        String[] taskFromAndTo = userInput.trim().split("/from");

        if (taskFromAndTo.length > 2) {
            throw new DukeException("Please enter format event (task) /from (yyyy-mm-dd hhmm) "
                    + "/to (yyyy-mm-dd hhmm)");
        } else if (taskFromAndTo[0].trim().isEmpty()) {
            throw new DukeException("Empty task description. Please enter format event (task) /from "
                    + "(yyyy-mm-dd hhmm) /to (yyyy-mm-dd hhmm)");
        } else if (taskFromAndTo.length == 1) {
            throw new DukeException("Please enter format event (task) /from (yyyy-mm-dd hhmm) "
                    + "/to (yyyy-mm-dd hhmm)");
        }

        String[] fromAndTo = taskFromAndTo[1].trim().split("/to");

        if (fromAndTo.length > 2) {
            throw new DukeException("Please enter format event (task) /from (yyyy-mm-dd hhmm)"
                    + " /to (yyyy-mm-dd hhmm)");
        } else if (fromAndTo[0].trim().isEmpty()) {
            throw new DukeException("Empty timing. Please enter format event (task) /from "
                    + "(yyyy-mm-dd hhmm) /to (yyyy-mm-dd hhmm)");
        } else if (fromAndTo.length == 1) {
            throw new DukeException("Empty timing. Please enter format event (task) /from "
                    + "(yyyy-mm-dd hhmm) /to (yyyy-mm-dd hhmm)");
        }

        String taskString = taskFromAndTo[0].trim();
        String fromString = fromAndTo[0].trim();
        String toString = fromAndTo[1].trim();
        String dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

        if (Pattern.matches(dateTimePattern, fromString) && Pattern.matches(dateTimePattern, toString)) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm")
                        .withResolverStyle(ResolverStyle.STRICT);
                LocalDateTime dateTimeFrom = LocalDateTime.parse(fromString, formatter);
                LocalDateTime dateTimeTo = LocalDateTime.parse(toString, formatter);

                if (dateTimeTo.isBefore(dateTimeFrom)) {
                    throw new DukeException("Date and time of /to is before /from");
                }

                return new Event(taskString, dateTimeFrom, dateTimeTo);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date or time. Please enter date and time in the"
                        + " format:yyyy-mm-dd hhmm");
            }
        } else {
            throw new DukeException("Please enter date in the format (yyyy-mm-dd hhmm)");
        }
    }
}
