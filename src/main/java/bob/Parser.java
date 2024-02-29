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
import bob.command.ListKeywordCommand;
import bob.command.ListOnDateCommand;
import bob.command.MarkCommand;
import bob.exception.BobException;
import bob.exception.EmptyDescriptionException;
import bob.exception.InvalidCommandException;
import bob.exception.InvalidDateTimeException;
import bob.exception.InvalidDaysException;
import bob.exception.InvalidTaskIndexException;
import bob.exception.ParameterNotFoundException;

/**
 * Utility class to make sense of the user command.
 */
public class Parser {
    public static final String COMMAND_EXIT = "exit";

    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_UNMARK = "unmark";

    private static final String PATTERN_DATE = "d/M/yyyy";
    private static final String PATTERN_DATE_TIME = PATTERN_DATE + " HHmm";

    private static final DateTimeFormatter FORMATTER_DATE =
            DateTimeFormatter.ofPattern(PATTERN_DATE);
    private static final DateTimeFormatter FORMATTER_DATE_TIME =
            DateTimeFormatter.ofPattern(PATTERN_DATE_TIME);

    private static String[] splitByParameter(String[] splitString, String parameter) throws ParameterNotFoundException {
        String[] newSplitString = splitString[0].split(" /" + parameter + ' ', 2);
        if (newSplitString.length == 1) {
            // This implies the last missing parameter will be displayed, rather than the first
            throw new ParameterNotFoundException(parameter);
        }
        return newSplitString;
    }

    /**
     * Extracts the description and parameters from a given command.
     *
     * @param parametersString The given command excluding the first word (the command itself).
     * @param parameters The parameters to extract.
     * @return The description and the extracted parameters, respecting the indices of the specified parameters.
     * @throws ParameterNotFoundException If one of the parameters to extract has not been found.
     */
    private static String[] extractParameters(
            String parametersString, String[] parameters) throws ParameterNotFoundException {
        // TODO: this method is a cruft and might be rewritten
        int n = parameters.length;
        String[] result = new String[n + 1];

        // Split the string repeatedly to extract the rightmost parameter
        String[] splitString = new String[] { parametersString };
        for (int i = n - 1; i >= 0; i--) {
            splitString = splitByParameter(splitString, parameters[i]);
            result[i + 1] = splitString[1];
        }

        // Assign result[0] to be the description before returning result
        result[0] = splitString[0];
        return result;
    }

    private static Command parseListOn(String onString) throws InvalidDateTimeException {
        try {
            LocalDate on = LocalDate.parse(onString, FORMATTER_DATE);
            return new ListOnDateCommand(on);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(PATTERN_DATE, e.getParsedString());
        }
    }

    private static Command parseListDueIn(String dueInString) throws InvalidDaysException {
        try {
            int days = Integer.parseInt(dueInString);
            return new ListDueInCommand(days);
        } catch (NumberFormatException e) {
            throw new InvalidDaysException(dueInString);
        }
    }

    /**
     * Makes sense of the given user command that is either a "list", "list on" or "list due in".
     *
     * @param commandArgs The given user command split into the command itself and the remaining description.
     * @return A <code>Command</code> object corresponding to the parsed command.
     * @throws ParameterNotFoundException If command has description but is missing the "on" and "due_in" parameters.
     * @throws InvalidDateTimeException If the user has given an invalid date and time.
     * @throws InvalidDaysException If the user has given an invalid number of days.
     */
    private static Command parseList(
            String[] commandArgs) throws ParameterNotFoundException, InvalidDateTimeException, InvalidDaysException {
        if (commandArgs.length == 1) {
            return new ListCommand();
        }
        // TODO: use extractParameters once it has been rewritten
        String remaining = commandArgs[1];

        // Try to extract "on" and "due_in" from the remaining portion of the command
        String[] onSplit = remaining.split("/on ", 2);
        String[] dueInSplit = remaining.split("/due_in ", 2);

        // Check which parameter is being extracted
        boolean hasOn = onSplit.length > 1;
        boolean hasDueIn = dueInSplit.length > 1;

        // Return the corresponding Command depending on the parameter extracted
        if (!hasOn && !hasDueIn) {
            throw new ParameterNotFoundException(new String[] { "on", "due_in" });
        }
        return hasOn ? parseListOn(onSplit[1]) : parseListDueIn(dueInSplit[1]);
    }

    /**
     * Makes sense of the given user command that is either a "mark", "unmark" or "delete".
     *
     * @param commandArgs The given user command split into the command itself and the remaining description.
     * @return A <code>Command</code> object corresponding to the parsed command.
     * @throws InvalidTaskIndexException If the user has given an invalid task index.
     * @throws EmptyDescriptionException If the given command does not have a description.
     */
    private static Command parseDeleteOrMark(
            String[] commandArgs) throws InvalidTaskIndexException, EmptyDescriptionException {
        try {
            int taskIndex = Integer.parseInt(commandArgs[1]) - 1;
            boolean isDelete = commandArgs[0].equals(Parser.COMMAND_DELETE);
            boolean isMark = commandArgs[0].equals(Parser.COMMAND_MARK);
            return isDelete ? new DeleteCommand(taskIndex) : new MarkCommand(taskIndex, isMark);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException(commandArgs[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException(commandArgs[0]);
        }
    }

    private static Command parseAddDeadline(
            String commandArgs) throws ParameterNotFoundException, InvalidDateTimeException {
        try {
            String[] parameters = Parser.extractParameters(commandArgs, new String[]{ "by" });
            String description = parameters[0];
            LocalDateTime by = LocalDateTime.parse(parameters[1], FORMATTER_DATE_TIME);
            return new AddDeadlineCommand(description, by);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(PATTERN_DATE_TIME, e.getParsedString());
        }
    }

    private static Command parseAddEvent(
            String commandArgs) throws ParameterNotFoundException, InvalidDateTimeException {
        try {
            String[] parameters = Parser.extractParameters(commandArgs, new String[] { "from", "to" });
            String description = parameters[0];
            LocalDateTime from = LocalDateTime.parse(parameters[1], FORMATTER_DATE_TIME);
            LocalDateTime to = LocalDateTime.parse(parameters[2], FORMATTER_DATE_TIME);
            return new AddEventCommand(description, from, to);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(PATTERN_DATE_TIME, e.getParsedString());
        }
    }

    /**
     * Makes sense of the given user command that is either a "todo", "deadline" or "event".
     *
     * @param commandArgs The given user command split into the command itself and the remaining description.
     * @return A <code>Command</code> object corresponding to the parsed command.
     * @throws InvalidDateTimeException If the user has given an invalid date and time.
     * @throws EmptyDescriptionException If the given command does not have a description.
     * @throws ParameterNotFoundException If the given command does not have the expected parameters.
     */
    private static Command parseAdd(
            String[] commandArgs) throws InvalidDateTimeException,
            EmptyDescriptionException, ParameterNotFoundException {
        // Add command without a description
        if (commandArgs.length == 1) {
            throw new EmptyDescriptionException(commandArgs[0]);
        }

        // TODO: can be improved by mapping each task type to a list of parameters
        // Undefined behaviour when there are multiple instances of the same parameter
        String taskType = commandArgs[0];

        // Return the corresponding Command depending on the type of task to be added
        switch (taskType) {
        case COMMAND_TODO:
            return new AddTodoCommand(commandArgs[1]);
        case COMMAND_DEADLINE:
            return parseAddDeadline(commandArgs[1]);
        default:
            return parseAddEvent(commandArgs[1]);
        }
    }

    /**
     * Makes sense of the "find" command.
     *
     * @param commandArgs The given user command split into the command itself and the remaining description.
     * @return A <code>Command</code> object corresponding to the parsed command.
     * @throws EmptyDescriptionException If the given command does not have a description.
     */
    public static Command parseFind(String[] commandArgs) throws EmptyDescriptionException {
        // Find command without a description
        if (commandArgs.length == 1) {
            throw new EmptyDescriptionException(commandArgs[0]);
        }

        // The remaining portion of the command is the keyword
        String keyword = commandArgs[1];
        return new ListKeywordCommand(keyword);
    }

    /**
     * Makes sense of the given user command.
     *
     * @param input The given user command.
     * @return A <code>Command</code> object corresponding to the parsed command.
     * @throws BobException If there is an error parsing the given user command.
     */
    public static Command parse(String input) throws BobException {
        String[] commandArgs = input.trim().split(" ", 2);

        // Set command to be the command entered by the user, rather than the entire line of string
        String command = commandArgs[0];

        // Parse the command differently depending on the type of command encountered
        switch (command) {
        case COMMAND_LIST:
            return parseList(commandArgs);
        case COMMAND_DELETE:
            // Fallthrough
        case COMMAND_UNMARK:
            // Fallthrough
        case COMMAND_MARK:
            return parseDeleteOrMark(commandArgs);
        case COMMAND_TODO:
            // Fallthrough
        case COMMAND_DEADLINE:
            // Fallthrough
        case COMMAND_EVENT:
            return parseAdd(commandArgs);
        case COMMAND_FIND:
            return parseFind(commandArgs);
        case COMMAND_EXIT:
            return new ExitCommand();
        default:
            throw new InvalidCommandException();
        }
    }
}
