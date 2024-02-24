package arc.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import arc.commands.Command;
import arc.commands.Command.CommandType;
import arc.commands.CommandBye;
import arc.commands.CommandDeadline;
import arc.commands.CommandDelete;
import arc.commands.CommandEvent;
import arc.commands.CommandFind;
import arc.commands.CommandList;
import arc.commands.CommandMark;
import arc.commands.CommandSortDeadline;
import arc.commands.CommandTodo;
import arc.commands.CommandUnknown;
import arc.commands.CommandUnmark;
import arc.exceptions.ArcException;
import arc.exceptions.parser.InvalidArgumentException;
import arc.tasks.Deadline;
import arc.tasks.Event;
import arc.tasks.Todo;

/**
 * Parses user input into commands for the Arc application.
 * This class provides static methods to interpret strings of user input
 * and convert them into executable Command objects.
 */
public class Parser {

    /**
     * Checks if the command arguments is of insufficient length.
     *
     * @return true if insufficient, false otherwise
     */
    private static boolean isCommandArgsLengthInsufficient(String[] commandArgs, int minLength) {
        return commandArgs.length < minLength;
    }

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
     * @param commandArgs The arguments passed with the command.
     * @return A CommandMark instance with the specified task index.
     * @throws ArcException If the argument is missing or not an integer.
     */
    private static Command parseCommandMark(String[] commandArgs) throws ArcException {
        if (isCommandArgsLengthInsufficient(commandArgs, 1)) {
            throw new InvalidArgumentException("task number", "is missing");
        }

        int taskNum;
        try {
            taskNum = Integer.parseInt(commandArgs[0]);
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidArgumentException("task number", "cannot be parsed to an integer",
                numberFormatException.getMessage());
        }

        int taskIndex = taskNum - 1;

        return new CommandMark(taskIndex);
    }

    /**
     * Parses the "unmark" command and its arguments.
     *
     * @param commandArgs The arguments passed with the command.
     * @return A CommandUnmark instance with the specified task index.
     * @throws ArcException If the argument is missing or not an integer.
     */
    private static Command parseCommandUnmark(String[] commandArgs) throws ArcException {
        if (isCommandArgsLengthInsufficient(commandArgs, 1)) {
            throw new InvalidArgumentException("task number", "is missing");
        }

        int taskNum;
        try {
            taskNum = Integer.parseInt(commandArgs[0]);
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidArgumentException("task number", "cannot be parsed to an integer",
                numberFormatException.getMessage());
        }

        int taskIndex = taskNum - 1;

        return new CommandUnmark(taskIndex);
    }

    /**
     * Parses the "todo" command and its arguments.
     *
     * @param commandArgs The arguments passed with the command.
     * @return A CommandTodo instance with the specified description.
     * @throws ArcException If the task description is missing.
     */
    private static Command parseCommandTodo(String[] commandArgs) throws ArcException {
        if (isCommandArgsLengthInsufficient(commandArgs, 1)) {
            throw new InvalidArgumentException("task description", "is missing");
        }

        String description = String.join(" ", commandArgs);

        Todo todo = new Todo(description);

        return new CommandTodo(todo);
    }


    private static Deadline getDeadline(String[] commandArgs) throws ArcException {
        List<String> commandArgsList = Arrays.asList(commandArgs);

        String byDelim = "/by";
        int byIndex = commandArgsList.indexOf(byDelim);

        boolean isByIndexNotFound = byIndex == -1;
        boolean isByIndexLastArg = byIndex == commandArgs.length - 1;

        if (isByIndexNotFound || isByIndexLastArg) {
            throw new InvalidArgumentException("/by", "not found");
        }

        String description = String.join(" ", Arrays.copyOfRange(commandArgs, 0, byIndex));

        String byStr = commandArgs[byIndex + 1];
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
     * @param commandArgs The arguments passed with the command.
     * @return A CommandDeadline instance with the specified task.
     * @throws ArcException If the task description or deadline is missing or invalid.
     */
    private static Command parseCommandDeadline(String[] commandArgs) throws ArcException {
        if (isCommandArgsLengthInsufficient(commandArgs, 2)) {
            throw new InvalidArgumentException("task description, /by", "are missing");
        }

        Deadline deadline = getDeadline(commandArgs);

        return new CommandDeadline(deadline);
    }

    private static Event getEvent(String[] commandArgs) throws ArcException {
        List<String> commandArgsList = Arrays.asList(commandArgs);

        String fromDelim = "/from";
        String toDelim = "/to";

        int fromIndex = commandArgsList.indexOf(fromDelim);
        int toIndex = commandArgsList.indexOf(toDelim);

        boolean isFromIndexNotFound = fromIndex == -1;
        boolean isFromIndexLastArg = fromIndex == commandArgs.length - 1;

        if (isFromIndexNotFound || isFromIndexLastArg) {
            throw new InvalidArgumentException("/from", "not found");
        }

        boolean isToIndexNotFound = toIndex == -1;
        boolean isToIndexLastArg = toIndex == commandArgs.length - 1;

        if (isToIndexNotFound || isToIndexLastArg) {
            throw new InvalidArgumentException("/to", "not found");
        }

        String fromStr = commandArgs[fromIndex + 1];
        String toStr = commandArgs[toIndex + 1];

        String description = String.join(" ",
            Arrays.copyOfRange(commandArgs, 0, Math.min(fromIndex, toIndex)));

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
     * @param commandArgs The arguments passed with the command.
     * @return A CommandEvent instance with the specified task.
     * @throws ArcException If the task description, start time, or end time is missing or invalid.
     */
    private static Command parseCommandEvent(String[] commandArgs) throws ArcException {
        if (isCommandArgsLengthInsufficient(commandArgs, 3)) {
            throw new InvalidArgumentException("task description, /from, /to", "are missing");
        }

        Event event = getEvent(commandArgs);
        return new CommandEvent(event);
    }

    /**
     * Parses the "delete" command and its arguments.
     *
     * @param commandArgs The arguments passed with the command.
     * @return A CommandDelete instance with the specified task index.
     * @throws ArcException If the argument is missing or not an integer.
     */
    private static Command parseCommandDelete(String[] commandArgs) throws ArcException {
        if (isCommandArgsLengthInsufficient(commandArgs, 1)) {
            throw new InvalidArgumentException("task number", "is missing");
        }

        int taskNum;
        try {
            taskNum = Integer.parseInt(commandArgs[0]);
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidArgumentException("task number", "cannot be parsed to an integer",
                numberFormatException.getMessage());
        }

        int taskIndex = taskNum - 1;

        return new CommandDelete(taskIndex);
    }

    /**
     * Parses the "find" command and its arguments.
     *
     * @param commandArgs The arguments passed with the command.
     * @return A CommandFind instance with the specified keyword.
     * @throws ArcException If the search keyword is missing.
     */
    private static Command parseCommandFind(String[] commandArgs) throws ArcException {
        if (isCommandArgsLengthInsufficient(commandArgs, 1)) {
            throw new InvalidArgumentException("search keyword", "is missing");
        }

        String keyword = commandArgs[0];

        return new CommandFind(keyword);
    }

    /**
     * Parses the "sort-deadlines" command and its arguments.
     */
    private static Command parseCommandSortDeadlines() {
        return new CommandSortDeadline();
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
     * @throws ArcException If the input does not conform to the expected format for known commands.
     */
    public static Command parse(String fullCommand) throws ArcException {
        // Extract arguments
        String fullCommandStripped = fullCommand.strip();
        String[] args = fullCommandStripped.split(" ");

        // Extract command type string
        String commandTypeStr = args[0];

        // Create command type enum
        CommandType commandType;
        try {
            commandType = CommandType.fromString(commandTypeStr);
        } catch (IllegalArgumentException illegalArgumentException) {
            return parseCommandUnknown();
        }

        // Extract command args
        String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);

        // Create command type object
        Command command;
        switch (commandType) {
        case LIST:
            command = parseCommandList();
            break;
        case BYE:
            command = parseCommandBye();
            break;
        case MARK:
            command = parseCommandMark(commandArgs);
            break;
        case UNMARK:
            command = parseCommandUnmark(commandArgs);
            break;
        case TODO:
            command = parseCommandTodo(commandArgs);
            break;
        case DEADLINE:
            command = parseCommandDeadline(commandArgs);
            break;
        case EVENT:
            command = parseCommandEvent(commandArgs);
            break;
        case DELETE:
            command = parseCommandDelete(commandArgs);
            break;
        case FIND:
            command = parseCommandFind(commandArgs);
            break;
        case SORT_DEADLINES:
            command = parseCommandSortDeadlines();
            break;
        default:
            command = parseCommandUnknown();
            break;
        }

        return command;
    }
}
