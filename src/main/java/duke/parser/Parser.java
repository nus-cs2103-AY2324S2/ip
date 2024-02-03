package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.commands.Command;
import duke.commands.CommandBye;
import duke.commands.CommandDeadline;
import duke.commands.CommandDelete;
import duke.commands.CommandEvent;
import duke.commands.CommandList;
import duke.commands.CommandMark;
import duke.commands.CommandTodo;
import duke.commands.CommandUnknown;
import duke.commands.CommandUnmark;
import duke.exceptions.DukeException;
import duke.exceptions.parser.InvalidArgumentException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

/**
 * Parses user input into commands for the Duke application.
 * This class provides static methods to interpret strings of user input
 * and convert them into executable Command objects.
 */
public class Parser {

    /**
     * Parses the "list" command.
     *
     * @return A CommandList instance.
     */
    private static Command parseCommandList() {
        return new CommandList();
    }

    /**
     * Parses the "bye" command.
     *
     * @return A CommandBye instance.
     */
    private static Command parseCommandBye() {
        return new CommandBye();
    }

    /**
     * Parses the "mark" command and its arguments.
     *
     * @param args The arguments passed with the command.
     * @return A CommandMark instance with the specified task index.
     * @throws DukeException If the argument is missing or not an integer.
     */
    private static Command parseCommandMark(String[] args) throws DukeException {
        try {
            if (args.length < 2) {
                throw new InvalidArgumentException("task number", "is missing");
            }

            int taskNum = Integer.parseInt(args[1]);
            int taskIndex = taskNum - 1;

            return new CommandMark(taskIndex);
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidArgumentException("task number", "cannot be parsed to an integer.",
                numberFormatException.getMessage());
        }
    }

    /**
     * Parses the "unmark" command and its arguments.
     *
     * @param args The arguments passed with the command.
     * @return A CommandUnmark instance with the specified task index.
     * @throws DukeException If the argument is missing or not an integer.
     */
    private static Command parseCommandUnmark(String[] args) throws DukeException {
        try {
            if (args.length < 2) {
                throw new InvalidArgumentException("task number", "is missing");
            }

            int taskNum = Integer.parseInt(args[1]);
            int taskIndex = taskNum - 1;

            return new CommandUnmark(taskIndex);
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidArgumentException("task number", "cannot be parsed to an integer",
                numberFormatException.getMessage());
        }
    }

    /**
     * Parses the "todo" command and its arguments.
     *
     * @param args The arguments passed with the command.
     * @return A CommandTodo instance with the specified description.
     * @throws DukeException If the task description is missing.
     */
    private static Command parseCommandTodo(String[] args) throws DukeException {
        if (args.length < 2) {
            throw new InvalidArgumentException("task description", "is missing");
        }

        String[] taskArgs = Arrays.copyOfRange(args, 1, args.length);
        String description = String.join(" ", taskArgs);

        Todo todo = new Todo(description);

        return new CommandTodo(todo);
    }


    private static Deadline getDeadline(int byIndex, String argsStr, String byDelim) throws DukeException {
        if (byIndex == -1) {
            throw new InvalidArgumentException("/by", "not found");
        }

        String description = argsStr.substring(0, byIndex).strip();

        String byStr = argsStr.substring(byIndex + byDelim.length()).strip();
        LocalDate by;
        try {
            by = LocalDate.parse(byStr);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new InvalidArgumentException("/by", "is not a valid date", dateTimeParseException.getMessage());
        }

        return new Deadline(description, by);
    }

    /**
     * Parses the "deadline" command and its arguments.
     *
     * @param args The arguments passed with the command.
     * @return A CommandDeadline instance with the specified task.
     * @throws DukeException If the task description or deadline is missing or invalid.
     */
    private static Command parseCommandDeadline(String[] args) throws DukeException {
        if (args.length < 3) {
            throw new InvalidArgumentException("task description, /by", "are missing");
        }

        String[] taskArgs = Arrays.copyOfRange(args, 1, args.length);
        String argsStr = String.join(" ", taskArgs);

        String byDelim = "/by";
        int byIndex = argsStr.indexOf(byDelim);
        Deadline deadline = getDeadline(byIndex, argsStr, byDelim);
        return new CommandDeadline(deadline);
    }

    private static Event getEvent(String fromStr, String toStr, String description) throws DukeException {
        LocalDate from;
        LocalDate to;
        try {
            from = LocalDate.parse(fromStr);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new InvalidArgumentException("/from", "is not a valid date", dateTimeParseException.getMessage());
        }
        try {
            to = LocalDate.parse(toStr);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new InvalidArgumentException("/to", "is not a valid date", dateTimeParseException.getMessage());
        }

        return new Event(description, from, to);
    }

    /**
     * Parses the "event" command and its arguments.
     *
     * @param args The arguments passed with the command.
     * @return A CommandEvent instance with the specified task.
     * @throws DukeException If the task description, start time, or end time is missing or invalid.
     */
    private static Command parseCommandEvent(String[] args) throws DukeException {
        if (args.length < 4) {
            throw new InvalidArgumentException("task description, /from, /to", "are missing");
        }

        String[] taskArgs = Arrays.copyOfRange(args, 1, args.length);
        String argsStr = String.join(" ", taskArgs);

        String fromDelim = "/from";
        String toDelim = "/to";

        int fromIndex = argsStr.indexOf(fromDelim);
        int toIndex = argsStr.indexOf(toDelim);

        if (fromIndex == -1) {
            throw new InvalidArgumentException("/from", "not found");
        }

        if (toIndex == -1) {
            throw new InvalidArgumentException("/to", "not found");
        }

        if (fromIndex > toIndex) {
            throw new InvalidArgumentException("/from", "must come before '/to'");
        }

        String fromStr = argsStr.substring(fromIndex + fromDelim.length(), toIndex).strip();
        String toStr = argsStr.substring(toIndex + toDelim.length()).strip();

        String description = argsStr.substring(0, fromIndex).strip();
        Event event = getEvent(fromStr, toStr, description);
        return new CommandEvent(event);
    }

    /**
     * Parses the "delete" command and its arguments.
     *
     * @param args The arguments passed with the command.
     * @return A CommandDelete instance with the specified task index.
     * @throws DukeException If the argument is missing or not an integer.
     */
    private static Command parseCommandDelete(String[] args) throws DukeException {
        try {
            if (args.length < 2) {
                throw new InvalidArgumentException("task number", "is missing");
            }

            int taskNum = Integer.parseInt(args[1]);
            int taskIndex = taskNum - 1;

            return new CommandDelete(taskIndex);
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidArgumentException("task number", "cannot be parsed to an integer",
                numberFormatException.getMessage());
        }
    }

    /**
     * Parses an unknown command.
     *
     * @return A CommandUnknown instance.
     */
    private static Command parseCommandUnknown() {
        return new CommandUnknown();
    }

    /**
     * Parses the full command input by the user into a Command object.
     *
     * @param fullCommand The full line of input from the user.
     * @return A Command object representing the user's intent.
     * @throws DukeException If the input does not conform to the expected format for known commands.
     */
    public static Command parse(String fullCommand) throws DukeException {
        fullCommand = fullCommand.strip();

        String[] args = fullCommand.split(" ");
        String commandType = args[0];

        Command command;
        switch (commandType) {
        case "list":
            command = parseCommandList();
            break;
        case "bye":
            command = parseCommandBye();
            break;
        case "mark":
            command = parseCommandMark(args);
            break;
        case "unmark":
            command = parseCommandUnmark(args);
            break;
        case "todo":
            command = parseCommandTodo(args);
            break;
        case "deadline":
            command = parseCommandDeadline(args);
            break;
        case "event":
            command = parseCommandEvent(args);
            break;
        case "delete":
            command = parseCommandDelete(args);
            break;
        default:
            command = parseCommandUnknown();
            break;
        }

        return command;
    }
}
