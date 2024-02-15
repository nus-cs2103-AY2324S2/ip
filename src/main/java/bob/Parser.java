package bob;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bob.command.AddDeadlineCommand;
import bob.command.AddEventCommand;
import bob.command.AddTodoCommand;
import bob.command.Command;
import bob.command.DeleteCommand;
import bob.command.ExitCommand;
import bob.command.ListCommand;
import bob.command.ListDueInCommand;
import bob.command.ListOnDateCommand;
import bob.command.MarkCommand;
import bob.exception.BobException;
import bob.exception.EmptyDescriptionException;
import bob.exception.InvalidCommandException;
import bob.exception.InvalidDateTimeException;
import bob.exception.InvalidDaysException;
import bob.exception.InvalidTaskIndexException;
import bob.exception.ParameterNotFoundException;

public class Parser {
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_UNMARK = "unmark";

    private static final String PATTERN_DATE = "d/M/yyyy";
    private static final String PATTERN_DATE_TIME = PATTERN_DATE + " HHmm";

    private static final DateTimeFormatter FORMATTER_DATE
            = DateTimeFormatter.ofPattern(PATTERN_DATE);
    private static final DateTimeFormatter FORMATTER_DATE_TIME
            = DateTimeFormatter.ofPattern(PATTERN_DATE_TIME);

    private static String[] extractParameters(String parametersString,
                                             String[] parameters) throws ParameterNotFoundException {
        // TODO: generalise the method to any given parametersString, desiredParameters and separator
        // TODO: perform ParameterNotFoundException checks correctly
        // TODO: return a HashMap that maps each desiredParameter to its value
        int n = parameters.length;
        String[] result = new String[n + 1];

        String[] splitString = new String[] { parametersString };
        for (int i = n - 1; i >= 0; i--) {
            splitString = splitString[0].split(" /" + parameters[i] + ' ', 2);
            if (splitString.length == 1) {
                // This implies the last missing parameter will be displayed, rather than the first
                throw new ParameterNotFoundException(parameters[i]);
            }
            result[i + 1] = splitString[1];
        }

        result[0] = splitString[0];

        return result;
    }

    private static Command parseList(
            String[] commandArgs) throws ParameterNotFoundException, InvalidDateTimeException, InvalidDaysException {
        if (commandArgs.length == 1) {
            return new ListCommand();
        } else {
            // TODO: use extractParameters once it has been generalised
            String remaining = commandArgs[1];

            String[] onSplit = remaining.split("/on ", 2);
            String[] dueInSplit = remaining.split("/due_in ", 2);

            boolean hasOn = onSplit.length > 1;
            boolean hasDueIn = dueInSplit.length > 1;

            if (hasOn) {
                try {
                    LocalDate on = LocalDate.parse(onSplit[1], FORMATTER_DATE);
                    return new ListOnDateCommand(on);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException(PATTERN_DATE, e.getParsedString());
                }
            } else if (hasDueIn) {
                try {
                    int days = Integer.parseInt(dueInSplit[1]);
                    return new ListDueInCommand(days);
                } catch (NumberFormatException e) {
                    throw new InvalidDaysException(dueInSplit[1]);
                }
            } else {
                throw new ParameterNotFoundException(new String[] { "on", "due_in" });
            }
        }
    }

    private static Command parseDeleteOrMark(
            String[] commandArgs) throws InvalidTaskIndexException, EmptyDescriptionException {
        try {
            int taskIndex = Integer.parseInt(commandArgs[1]) - 1;
            if (commandArgs[0].equals(Parser.COMMAND_DELETE)) {
                return new DeleteCommand(taskIndex);
            } else {
                return new MarkCommand(taskIndex, commandArgs[0].equals(Parser.COMMAND_MARK));
            }
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException(commandArgs[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException(commandArgs[0]);
        }
    }

    private static Command parseAdd(
            String[] commandArgs) throws InvalidDateTimeException,
            EmptyDescriptionException, ParameterNotFoundException {
        if (commandArgs.length == 1) {
            throw new EmptyDescriptionException(commandArgs[0]);
        }

        // TODO: map each task type to a list of parameters
        // Undefined behaviour when there are multiple instances of the same parameter
        // e.g. event project meeting /from 2019-10-02 /to 2019-10-02 /from 2019-10-04 /to 2019-10-04
        String taskType = commandArgs[0];
        String[] parameters;
        String description;

        try {
            switch (taskType) {
            case COMMAND_TODO:
                parameters = Parser.extractParameters(commandArgs[1], new String[]{});
                description = parameters[0];
                return new AddTodoCommand(description);
            case COMMAND_DEADLINE:
                parameters = Parser.extractParameters(commandArgs[1], new String[]{ "by" });
                description = parameters[0];
                LocalDateTime by = LocalDateTime.parse(parameters[1], FORMATTER_DATE_TIME);
                return new AddDeadlineCommand(description, by);
            default:
                parameters = Parser.extractParameters(commandArgs[1], new String[] { "from", "to" });
                description = parameters[0];
                LocalDateTime from = LocalDateTime.parse(parameters[1], FORMATTER_DATE_TIME);
                LocalDateTime to = LocalDateTime.parse(parameters[2], FORMATTER_DATE_TIME);
                return new AddEventCommand(description, from, to);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(PATTERN_DATE_TIME, e.getParsedString());
        }
    }

    public static Command parse(String command) throws BobException {
        // TODO: Use regex
        String[] commandArgs = command.trim().split(" ", 2);

        command = commandArgs[0];

        if (command.equals(Parser.COMMAND_EXIT)) {
            return new ExitCommand();
        }

        switch (command) {
        case Parser.COMMAND_LIST:
            return parseList(commandArgs);
        case Parser.COMMAND_DELETE:
            // Fallthrough
        case Parser.COMMAND_UNMARK:
            // Fallthrough
        case Parser.COMMAND_MARK:
            return parseDeleteOrMark(commandArgs);
        case Parser.COMMAND_TODO:
            // Fallthrough
        case Parser.COMMAND_DEADLINE:
            // Fallthrough
        case Parser.COMMAND_EVENT:
            return parseAdd(commandArgs);
        default:
            throw new InvalidCommandException();
        }
    }
}
