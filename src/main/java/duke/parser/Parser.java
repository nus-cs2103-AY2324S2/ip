package duke.parser;

import duke.command.ByeCommand;
import duke.command.CheckCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.common.Messages;
import duke.exception.InvalidCommandFormatException;
import duke.exception.UnknownCommandException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern DEADLINE_COMMAND_FORMAT = Pattern.compile("(?<description>\\S+) /by (?<deadline>.*)");
    public static final Pattern ONE_ARG_COMMAND_FORMAT = Pattern.compile("(?<arg>\\S+)");
    public static final Pattern EVENT_COMMAND_FORMAT = Pattern.compile(
            "(?<description>\\S+) /from (?<startDate>.*) /to (?<endDate>.*)");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static Command parse(String input) throws UnknownCommandException, InvalidCommandFormatException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            throw new UnknownCommandException();
        }
        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");
        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new InvalidCommandFormatException(
                        String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.COMMAND_USAGE));
            }
            return new ListCommand();
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(arguments);
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);
        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);
        case MarkCommand.COMMAND_WORD:
            return prepareMarkOrUnmark(arguments, MarkCommand.COMMAND_WORD);
        case UnmarkCommand.COMMAND_WORD:
            return prepareMarkOrUnmark(arguments, UnmarkCommand.COMMAND_WORD);
        case ByeCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new InvalidCommandFormatException(
                        String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ByeCommand.COMMAND_USAGE));
            }
            return new ByeCommand();
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);
        case CheckCommand.COMMAND_WORD:
            return prepareCheck(arguments);
        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);
        default:
            throw new UnknownCommandException();
        }
    }

    private static Command prepareTodo(String arguments) throws InvalidCommandFormatException {
        Matcher matcher = ONE_ARG_COMMAND_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TodoCommand.COMMAND_USAGE));
        }


        String description = matcher.group("arg");
        return new TodoCommand(description);
    }

    private static Command prepareFind(String arguments) throws InvalidCommandFormatException {
        Matcher matcher = ONE_ARG_COMMAND_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.COMMAND_USAGE));
        }

        String arg = matcher.group("arg");
        return new FindCommand(arg);
    }

    private static Command prepareDelete(String arguments) throws InvalidCommandFormatException {
        Matcher matcher = ONE_ARG_COMMAND_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.COMMAND_USAGE));
        }


        String arg = matcher.group("arg");
        try {
            int taskNumber = Integer.parseInt(arg);
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.COMMAND_USAGE));
        }
    }

    private static Command prepareDeadline(String arguments) throws InvalidCommandFormatException {
        System.out.println(arguments);
        Matcher matcher = DEADLINE_COMMAND_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeadlineCommand.COMMAND_USAGE));
        }


        try {
            String description = matcher.group("description");
            String deadline = matcher.group("deadline");
            LocalDateTime deadlineTime = LocalDateTime.parse(deadline, dateTimeFormatter);
            return new DeadlineCommand(description, deadlineTime);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeadlineCommand.COMMAND_USAGE));
        }
    }


    private static Command prepareMarkOrUnmark(String arguments, String commandWord) throws
            InvalidCommandFormatException {
        Matcher matcher = ONE_ARG_COMMAND_FORMAT.matcher(arguments.trim());
        String commandUsage = commandWord.equals("mark") ? MarkCommand.COMMAND_USAGE : UnmarkCommand.COMMAND_USAGE;
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, commandUsage));
        }


        String arg = matcher.group("arg");
        try {
            int taskNumber = Integer.parseInt(arg);
            return commandWord.equals(MarkCommand.COMMAND_WORD) ? new MarkCommand(taskNumber) : new UnmarkCommand(
                    taskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, commandUsage));
        }
    }

    private static Command prepareEvent(String arguments) throws InvalidCommandFormatException {
        Matcher matcher = EVENT_COMMAND_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EventCommand.COMMAND_USAGE));
        }
        String description = matcher.group("description");
        String startDate = matcher.group("startDate");
        String endDate = matcher.group("endDate");
        try {
            return new EventCommand(description, LocalDateTime.parse(startDate, dateTimeFormatter),
                    LocalDateTime.parse(endDate, dateTimeFormatter));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EventCommand.COMMAND_USAGE));
        }
    }

    private static Command prepareCheck(String arguments) throws InvalidCommandFormatException {
        Matcher matcher = ONE_ARG_COMMAND_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.COMMAND_USAGE));
        }
        String arg = matcher.group("arg");
        try {
            LocalDate checkDate = LocalDate.parse(arg, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return new CheckCommand(checkDate);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.COMMAND_USAGE));
        }

    }
}
