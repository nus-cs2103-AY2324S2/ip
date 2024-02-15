package bob;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bob.command.*;
import bob.exception.BobException;
import bob.exception.EmptyDescriptionException;
import bob.exception.InvalidCommandException;
import bob.exception.InvalidDateTimeException;
import bob.exception.InvalidDaysException;
import bob.exception.InvalidTaskIndexException;
import bob.exception.ParameterNotFoundException;

public class Parser {
    private static final String EXIT = "exit";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";
    // TODO: Sort constants
    private static final String FIND = "find";

    private static final String DATE_PATTERN = "d/M/yyyy";
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern(DATE_PATTERN);

    private static final String DATETIME_PATTERN = DATE_PATTERN + " HHmm";
    private static final DateTimeFormatter DATETIME_FORMATTER
            = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

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
                    LocalDate on = LocalDate.parse(onSplit[1], DATE_FORMATTER);
                    return new ListOnDateCommand(on);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException(DATE_PATTERN, e.getParsedString());
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
            if (commandArgs[0].equals(Parser.DELETE)) {
                return new DeleteCommand(taskIndex);
            } else {
                return new MarkCommand(taskIndex, commandArgs[0].equals(Parser.MARK));
            }
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException(commandArgs[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException(commandArgs[0]);
        }
    }

    private static Command parseAdd(
            String[] commandArgs) throws InvalidDateTimeException, EmptyDescriptionException, ParameterNotFoundException {
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
            case TODO:
                parameters = Parser.extractParameters(commandArgs[1], new String[]{});
                description = parameters[0];
                return new AddTodoCommand(description);
            case DEADLINE:
                parameters = Parser.extractParameters(commandArgs[1], new String[]{ "by" });
                description = parameters[0];
                LocalDateTime by = LocalDateTime.parse(parameters[1], DATETIME_FORMATTER);
                return new AddDeadlineCommand(description, by);
            default:
                parameters = Parser.extractParameters(commandArgs[1], new String[] { "from", "to" });
                description = parameters[0];
                LocalDateTime from = LocalDateTime.parse(parameters[1], DATETIME_FORMATTER);
                LocalDateTime to = LocalDateTime.parse(parameters[2], DATETIME_FORMATTER);
                return new AddEventCommand(description, from, to);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(DATETIME_PATTERN, e.getParsedString());
        }
    }

    public static Command parseFind(String[] commandArgs) throws EmptyDescriptionException {
        // TODO: Add comments
        if (commandArgs.length == 1) {
            throw new EmptyDescriptionException(commandArgs[0]);
        }

        String keyword = commandArgs[1];
        return new ListKeywordCommand(keyword);
    }

    public static Command parse(String command) throws BobException {
        // TODO: Remove Parser. since we are in the same class
        // TODO: Use regex
        String[] commandArgs = command.trim().split(" ", 2);

        command = commandArgs[0];

        if (command.equals(Parser.EXIT)) {
            return new ExitCommand();
        }

        switch (command) {
        case Parser.LIST:
            return parseList(commandArgs);
        case Parser.DELETE:
            // Fallthrough
        case Parser.UNMARK:
            // Fallthrough
        case Parser.MARK:
            return parseDeleteOrMark(commandArgs);
        case Parser.TODO:
            // Fallthrough
        case Parser.DEADLINE:
            // Fallthrough
        case Parser.EVENT:
            return parseAdd(commandArgs);
        case Parser.FIND:
            return parseFind(commandArgs);
        default:
            throw new InvalidCommandException();
        }
    }
}
