package parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import command.AddCommand;
import command.Command;
import command.CommandType;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import exception.BuddyException;
import task.Deadline;
import task.Event;
import task.Todo;


/**
 * Represents Parser component of buddy.Buddy, parsing inputs given by user.
 */
public class Parser {
    protected static final DateTimeFormatter DATE_TIME_PARSE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Parses input given by user.
     *
     * @param fullCommand Input given by user.
     * @return Command to be executed.
     * @throws BuddyException If input given by user is incomplete or unrecognised.
     */
    public static Command parse(String fullCommand) throws BuddyException {
        Command c = null;
        if (!fullCommand.isEmpty()) {
            String[] commandParts = fullCommand.split(" ", 2);
            CommandType commandWord = getCommandType(commandParts[0].trim());

            switch (commandWord) {
            case BYE:
                c = new ExitCommand();
                break;
            case LIST:
                c = new ListCommand();
                break;
            case MARK:
                c = parseMark(commandParts);
                break;
            case UNMARK:
                c = parseUnmark(commandParts);
                break;
            case DELETE:
                c = parseDelete(commandParts);
                break;
            case TODO:
                c = parseTodo(commandParts);
                break;
            case DEADLINE:
                c = parseDeadline(commandParts);
                break;
            case EVENT:
                c = parseEvent(commandParts);
                break;
            case FIND:
                c = parseFind(commandParts);
                break;
            case EDIT:
                EditParser ep = parseEdit(commandParts);
                c = ep.parseEdit();
                break;
            default:
                throw new BuddyException("Not a valid command!");
            }
        }
        assert c != null : "must have value"; // variable c must be a valid Command object
        return c;
    }

    /**
     * Assigns type of command to input given by user.
     *
     * @param cmd Command given by user.
     * @return CommandType of command to be executed.
     */
    public static CommandType getCommandType(String cmd) {
        try {
            return CommandType.valueOf(cmd.trim().toUpperCase());
        } catch (IllegalArgumentException iae) {
            return CommandType.INVALID;
        }
    }

    private static Command parseDelete(String[] query) throws BuddyException {
        try {
            if (query.length == 1) {
                throw new BuddyException("I need a task number buddy!");
            }

            int index = Integer.parseInt(query[1].trim()) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException nfe) {
            throw new BuddyException("Not a valid task number buddy!");
        }
    }

    private static Command parseTodo(String[] query) throws BuddyException {
        if (query.length == 1) {
            throw new BuddyException("Please include a task!");
        }

        Todo todo = new Todo(query[1].trim());
        return new AddCommand(todo);
    }

    private static Command parseDeadline(String[] query) throws BuddyException {
        try {
            if (query.length == 1) {
                throw new BuddyException("Please include a task and deadline!");
            }

            String[] requestBody = query[1].split("/by", 2);
            if (requestBody.length <= 1 || requestBody[1].isEmpty()) {
                throw new BuddyException("Please include a deadline!");
            }

            LocalDateTime by = LocalDateTime.parse(requestBody[1].trim(), DATE_TIME_PARSE_FORMAT);
            Deadline deadline = new Deadline(requestBody[0], by);
            return new AddCommand(deadline);
        } catch (DateTimeException dte) {
            throw new BuddyException("Not a valid date format!");
        }
    }

    private static Command parseEvent(String[] query) throws BuddyException {
        try {
            if (query.length == 1) {
                throw new BuddyException("Please include a task and start/end times");
            }

            String[] requestBody = query[1].split("/from", 2);
            if (requestBody.length <= 1 || requestBody[1].isEmpty()) {
                throw new BuddyException("Please include start/end time!");
            }

            String[] requestTime = requestBody[1].split("/to", 2);
            if (requestTime.length <= 1 || requestTime[1].isEmpty()) {
                throw new BuddyException("Please include end time!");
            }

            LocalDateTime from = LocalDateTime.parse(requestTime[0].trim(), DATE_TIME_PARSE_FORMAT);
            LocalDateTime to = LocalDateTime.parse(requestTime[1].trim(), DATE_TIME_PARSE_FORMAT);
            Event event = new Event(requestBody[0].trim(), from, to);
            return new AddCommand(event);
        } catch (DateTimeException dte) {
            throw new BuddyException("Not a valid date format!");
        }
    }
    private static Command parseFind(String[] query) throws BuddyException {
        if (query.length == 1) {
            throw new BuddyException("What are you trying to find buddy?");
        }

        return new FindCommand(query[1].trim());
    }

    private static Command parseUnmark(String[] query) throws BuddyException {
        try {
            if (query.length == 1) {
                throw new BuddyException("I need a task number buddy!");
            }

            int index = Integer.parseInt(query[1].trim()) - 1;
            return new UnmarkCommand(index);
        } catch (NumberFormatException nfe) {
            throw new BuddyException("Not a valid task number buddy!");
        }
    }

    private static Command parseMark(String[] query) throws BuddyException {
        try {
            if (query.length == 1) {
                throw new BuddyException("I need a task number buddy!");
            }

            int index = Integer.parseInt(query[1].trim()) - 1;
            return new MarkCommand(index);
        } catch (NumberFormatException nfe) {
            throw new BuddyException("Not a valid task number buddy!");
        }
    }

    private static EditParser parseEdit(String[] query) throws BuddyException {
        try {
            if (query.length == 1) {
                throw new BuddyException("Incomplete command!");
            }

            String[] queryParts = query[1].split(" ", 2);
            if (queryParts.length == 1) {
                throw new BuddyException("Please include information to edit using /task, /by, /from or /to!");
            }

            String indexString = queryParts[0];
            String queryFlags = queryParts[1];

            int index = Integer.parseInt(indexString.trim()) - 1;
            return new EditParser(index, queryFlags);
        } catch (NumberFormatException nfe) {
            throw new BuddyException("Not a valid task number buddy!");
        }
    }
}
