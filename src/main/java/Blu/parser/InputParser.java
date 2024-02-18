package blu.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import blu.command.ByeCommand;
import blu.command.Command;
import blu.command.CommandType;
import blu.command.DeadlineCommand;
import blu.command.DeleteCommand;
import blu.command.EventCommand;
import blu.command.FindCommand;
import blu.command.InvalidCommand;
import blu.command.ListCommand;
import blu.command.MarkCommand;
import blu.command.ToDoCommand;
import blu.command.UnmarkCommand;
import blu.exception.IllegalCommandException;

/**
 * Parses user input into corresponding Command objects.
 */
public class InputParser {
    private static final DateTimeFormatter INPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static final int BASE_TOKENS_INDEX = 0;

    /**
     * Parses the given user input into a specific Command object.
     *
     * @param userInput The user input to be parsed.
     * @return The command corresponding to the user input.
     * @throws IllegalCommandException If the input cannot be parsed into a valid command.
     */
    public Command parseInput(String userInput) throws IllegalCommandException {
        String[] tokens = userInput.trim().split(" ");
        String cmdString = tokens[0];
        CommandType cmd = CommandType.fromString(cmdString);
        switch (cmd) {
        case LIST:
            return new ListCommand();
        case MARK:
            return prepareMarkCommand(tokens);
        case UNMARK:
            return prepareUnmarkCommand(tokens);
        case TODO:
            return prepareToDoCommand(tokens);
        case DEADLINE:
            return prepareDeadlineCommand(tokens);
        case EVENT:
            return prepareEventCommand(tokens);
        case FIND:
            return prepareFindCommand(tokens);
        case DELETE:
            return prepareDeleteCommand(tokens);
        case BYE:
            return new ByeCommand();
        default:
            return new InvalidCommand(cmdString);
        }
    }

    private int findParamIdx(String[] tokens, String param) {
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals(param)) {
                return i;
            }
        }
        return -1;
    }

    private String getParamValue(String[] tokens, int paramIdx, int endIdx) {
        String[] subArray = Arrays.copyOfRange(tokens, paramIdx + 1, endIdx);
        String paramVal = String.join(" ", subArray);
        return paramVal;
    }

    private boolean isParamEmpty(int startIdx, int endIdx) {
        if (startIdx + 1 == endIdx) {
            return true;
        }
        return false;
    }

    private boolean isNumberOfParamCorrect(String[] tokens, int expectedNumber) {
        if (tokens.length == expectedNumber) {
            return true;
        }
        return false;
    }

    private LocalDateTime parseDateTime(String dateTimeString) throws IllegalCommandException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, INPUT_DATETIME_FORMAT);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new IllegalCommandException("Invalid DateTime format.\n"
                    + "Please use dd-MM-yyyy HH:MM format.");
        }
    }

    private int parseInteger(String intString) throws IllegalCommandException {
        try {
            int result = Integer.parseInt(intString);
            return result;
        } catch (NumberFormatException e) {
            throw new IllegalCommandException(intString + " is not a valid integer!");
        }
    }

    private Command prepareMarkCommand(String[] tokens) throws IllegalCommandException {
        if (!isNumberOfParamCorrect(tokens, 2)) {
            throw new IllegalCommandException("Please specify a task number to mark\n"
                    + "Usage: mark <task_number>");
        }
        int markTaskIdx = parseInteger(tokens[1]);
        return new MarkCommand(markTaskIdx);
    }

    private Command prepareUnmarkCommand(String[] tokens) throws IllegalCommandException {
        if (!isNumberOfParamCorrect(tokens, 2)) {
            throw new IllegalCommandException("Please specify a task number to unmark\n"
                    + "Usage: unmark <task_number>");
        }
        int unmarkTaskIdx = parseInteger(tokens[1]);
        return new UnmarkCommand(unmarkTaskIdx);
    }

    private Command prepareToDoCommand(String[] tokens) throws IllegalCommandException {
        if (tokens.length < 2) {
            throw new IllegalCommandException("Description of a todo cannot be empty.\n"
                    + "Usage: todo <task_title>");
        }
        String todoTitle = getParamValue(tokens, BASE_TOKENS_INDEX, tokens.length);
        return new ToDoCommand(todoTitle);
    }

    private Command prepareDeadlineCommand(String[] tokens) throws IllegalCommandException {
        String byParam = "/by";
        int paramIdx = findParamIdx(tokens, byParam);
        if (paramIdx == -1) {
            throw new IllegalCommandException(byParam + " parameter not found!\n"
                    + "Usage: deadline <task_title> /by <datetime>");
        }

        if (isParamEmpty(BASE_TOKENS_INDEX, paramIdx)) {
            throw new IllegalCommandException("Description of a deadline cannot be empty.\n"
                    + "Usage: deadline <task_title> /by <datetime>");
        }

        String deadlineTitle = getParamValue(tokens, BASE_TOKENS_INDEX, paramIdx);
        if (isParamEmpty(paramIdx, tokens.length)) {
            throw new IllegalCommandException("Datetime of deadline cannot be empty.\n"
                    + "Usage: deadline <task_title> /by <datetime>");
        }

        String byStr = getParamValue(tokens, paramIdx, tokens.length);
        LocalDateTime byDateTime = parseDateTime(byStr);
        return new DeadlineCommand(deadlineTitle, byDateTime);
    }

    private Command prepareEventCommand(String[] tokens) throws IllegalCommandException {
        String fromParam = "/from";
        String toParam = "/to";
        int fromParamIdx = findParamIdx(tokens, fromParam);
        int toParamIdx = findParamIdx(tokens, toParam);
        if (fromParamIdx == -1 || toParamIdx == -1) {
            throw new IllegalCommandException(fromParam + " or " + toParam + " not found!\n"
                    + "Usage: event <task_title> /from <datetime> /to <datetime>");
        }

        if (isParamEmpty(BASE_TOKENS_INDEX, fromParamIdx)) {
            throw new IllegalCommandException("Description of event cannot be empty.\n"
                    + "Usage: event <task_title> /from <datetime> /to <datetime>");
        }

        String eventTitle = getParamValue(tokens, 0, fromParamIdx);
        if (isParamEmpty(fromParamIdx, toParamIdx) || isParamEmpty(toParamIdx, tokens.length)) {
            throw new IllegalCommandException("Datetimes of event cannot be empty.\n"
                    + "Usage: event <task_title> /from <datetime> /to <datetime>");
        }

        String fromStr = getParamValue(tokens, fromParamIdx, toParamIdx);
        LocalDateTime fromDateTime = parseDateTime(fromStr);
        String toStr = getParamValue(tokens, toParamIdx, tokens.length);
        LocalDateTime toDateTime = parseDateTime(toStr);
        if (fromDateTime.isAfter(toDateTime)) {
            throw new IllegalCommandException("From Datetime is later than To Datetime");
        }
        return new EventCommand(eventTitle, fromDateTime, toDateTime);
    }

    private Command prepareFindCommand(String[] tokens) throws IllegalCommandException {
        if (tokens.length < 2) {
            throw new IllegalCommandException("Search string cannot be empty.\n"
                    + "Usage: find <search_string>");
        }
        String searchString = getParamValue(tokens, BASE_TOKENS_INDEX, tokens.length);
        return new FindCommand(searchString);
    }

    private Command prepareDeleteCommand(String[] tokens) throws IllegalCommandException {
        if (!isNumberOfParamCorrect(tokens, 2)) {
            throw new IllegalCommandException("Please specify a task number to delete\n"
                    + "Usage: delete <task_number>");
        }
        int deleteTaskIdx = parseInteger(tokens[1]);
        return new DeleteCommand(deleteTaskIdx);
    }
}
