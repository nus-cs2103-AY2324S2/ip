package seiki.parser;

import static seiki.common.DateTime.DATE_TIME_FORMATTER;
import static seiki.common.DateTime.DATE_TIME_HELPER;
import static seiki.common.ErrorMessages.ERROR_MESSAGE_ADDITIONAL_INPUT;
import static seiki.common.ErrorMessages.ERROR_MESSAGE_EMPTY_INPUT;
import static seiki.common.ErrorMessages.ERROR_MESSAGE_INCORRECT_DATETIME_FORMAT;
import static seiki.common.ErrorMessages.ERROR_MESSAGE_INVALID_COMMAND;
import static seiki.common.ErrorMessages.ERROR_MESSAGE_MISSING_DATETIME;
import static seiki.common.ErrorMessages.ERROR_MESSAGE_MISSING_KEYWORD;
import static seiki.common.ErrorMessages.ERROR_MESSAGE_MISSING_TASK_NUMBER;
import static seiki.common.ErrorMessages.ERROR_MESSAGE_MISSING_TASK_TITLE;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seiki.commands.ByeCommand;
import seiki.commands.Command;
import seiki.commands.DeadlineCommand;
import seiki.commands.DeleteCommand;
import seiki.commands.EventCommand;
import seiki.commands.FindCommand;
import seiki.commands.HelpCommand;
import seiki.commands.ListCommand;
import seiki.commands.MarkCommand;
import seiki.commands.ToDoCommand;
import seiki.commands.UnmarkCommand;
import seiki.data.exception.SeikiException;

/**
 * Parses user input.
 */
public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<command>\\w+)(?<arguments>.*)");
    /**
     * Parses user input into command for execution.
     * @param input full user input string
     * @return the command based on the user input
     * @throws SeikiException when there is a missing input or incorrect format
     */
    public static Command parse(String input) throws SeikiException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        checkIfInputIsEmpty(input, ERROR_MESSAGE_EMPTY_INPUT);

        if (!matcher.matches()) {
            throw new SeikiException(ERROR_MESSAGE_INVALID_COMMAND);
        }

        String cmd = matcher.group("command");
        String args = matcher.group("arguments").stripLeading();

        assert !args.isEmpty() : "Argument should not be empty";

        switch (cmd) {
        case ListCommand.COMMAND_WORD:
            return parseListCommand(args);
        case MarkCommand.COMMAND_WORD:
            return parseMarkCommand(args);
        case UnmarkCommand.COMMAND_WORD:
            return parseUnmarkCommand(args);
        case ToDoCommand.COMMAND_WORD:
            return parseToDoCommand(args);
        case DeadlineCommand.COMMAND_WORD:
            return parseDeadlineCommand(args);
        case EventCommand.COMMAND_WORD:
            return parseEventCommand(args);
        case DeleteCommand.COMMAND_WORD:
            return parseDeleteCommand(args);
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case FindCommand.COMMAND_WORD:
            return parseFindCommand(args);
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        default:
            throw new SeikiException(ERROR_MESSAGE_INVALID_COMMAND);
        }
    }

    private static void checkIfArgMissing(int index, String helper) throws SeikiException {
        if (index == -1) {
            String errMsg = combineMessage(ERROR_MESSAGE_INVALID_COMMAND, helper);
            throw new SeikiException(errMsg);
        }
    }

    private static void checkIfInputIsEmpty(String args, String errMsg) throws SeikiException {
        if (args.isEmpty()) {
            throw new SeikiException(errMsg);
        }
    }
    private static void checkIfInputIsEmpty(String args, String errMsg, String helperMsg)
            throws SeikiException {
        if (args.isEmpty()) {
            throw new SeikiException(combineMessage(errMsg, helperMsg));
        }
    }

    private static void checkIfInputIsNotEmpty(String args, String helperMsg) throws SeikiException {
        if (!args.isEmpty()) {
            throw new SeikiException(combineMessage(ERROR_MESSAGE_ADDITIONAL_INPUT, helperMsg));
        }
    }

    private static void checkIfInputIsTooLong(ArrayList<String> args, String helperMsg) throws SeikiException {
        if (args.size() > 1) {
            throw new SeikiException(combineMessage(ERROR_MESSAGE_ADDITIONAL_INPUT, helperMsg));
        }
    }

    private static void checkIfInputIsMissing(String args, String errMsg, String helperMsg) throws SeikiException {
        if (args.isEmpty()) {
            throw new SeikiException(combineMessage(errMsg, helperMsg));
        }
    }

    private static String combineMessage(String msg1, String msg2) {
        return msg1 + "\n" + msg2;
    }

    private static LocalDateTime parseDateTimeFormat(String dateTimeStr) throws SeikiException {
        try {
            return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            String combinedMsg = combineMessage(ERROR_MESSAGE_INCORRECT_DATETIME_FORMAT, DATE_TIME_HELPER);
            throw new SeikiException(combinedMsg);
        }
    }

    private static DeadlineCommand parseDeadlineCommand(String args) throws SeikiException {
        checkIfArgMissing(args.indexOf("/by"), DeadlineCommand.COMMAND_HELPER);

        String taskTitle = args.substring(0, args.indexOf("/by"));
        String dateTimeStr = args.substring(args.indexOf("/by") + 3).trim();

        checkIfInputIsMissing(taskTitle, ERROR_MESSAGE_MISSING_TASK_TITLE, DeadlineCommand.COMMAND_HELPER);
        checkIfInputIsMissing(dateTimeStr, ERROR_MESSAGE_MISSING_DATETIME, DeadlineCommand.COMMAND_HELPER);

        LocalDateTime dateTime = parseDateTimeFormat(dateTimeStr);

        return new DeadlineCommand(taskTitle, dateTime);
    }

    private static DeleteCommand parseDeleteCommand(String args) throws SeikiException {
        ArrayList<String> argsList = new ArrayList<>(Arrays.asList(args.split(" ")));

        checkIfInputIsEmpty(args, ERROR_MESSAGE_MISSING_TASK_NUMBER, DeleteCommand.COMMAND_HELPER);
        checkIfInputIsTooLong(argsList, DeleteCommand.COMMAND_HELPER);

        return new DeleteCommand(argsList.get(0));
    }

    private static EventCommand parseEventCommand(String args) throws SeikiException {
        checkIfArgMissing(args.indexOf("/from"), EventCommand.COMMAND_HELPER);
        checkIfArgMissing(args.indexOf("/to"), EventCommand.COMMAND_HELPER);

        String taskTitle = args.substring(0, args.indexOf("/from"));
        String startDateTimeStr = args.substring(args.indexOf("/from") + 5, args.indexOf("/to")).trim();
        String endDateTimeStr = args.substring(args.indexOf("/to") + 3).trim();

        checkIfInputIsMissing(taskTitle, ERROR_MESSAGE_MISSING_TASK_TITLE, EventCommand.COMMAND_HELPER);
        checkIfInputIsMissing(startDateTimeStr, ERROR_MESSAGE_MISSING_DATETIME, EventCommand.COMMAND_HELPER);
        checkIfInputIsMissing(endDateTimeStr, ERROR_MESSAGE_MISSING_DATETIME, EventCommand.COMMAND_HELPER);

        LocalDateTime startDateTime = parseDateTimeFormat(startDateTimeStr);
        LocalDateTime endDateTime = parseDateTimeFormat(endDateTimeStr);

        return new EventCommand(taskTitle, startDateTime, endDateTime);
    }

    private static FindCommand parseFindCommand(String args) throws SeikiException {
        ArrayList<String> argsList = new ArrayList<>(Arrays.asList(args.split(" ")));

        checkIfInputIsEmpty(args, ERROR_MESSAGE_MISSING_KEYWORD, FindCommand.COMMAND_HELPER);
        checkIfInputIsTooLong(argsList, FindCommand.COMMAND_HELPER);

        return new FindCommand(argsList.get(0));
    }

    private static ListCommand parseListCommand(String args) throws SeikiException {
        checkIfInputIsNotEmpty(args, ListCommand.COMMAND_HELPER);

        return new ListCommand();
    }

    private static MarkCommand parseMarkCommand(String args) throws SeikiException {
        ArrayList<String> argsList = new ArrayList<>(Arrays.asList(args.split(" ")));

        checkIfInputIsEmpty(args, ERROR_MESSAGE_MISSING_TASK_NUMBER, MarkCommand.COMMAND_HELPER);
        checkIfInputIsTooLong(argsList, MarkCommand.COMMAND_HELPER);

        return new MarkCommand(argsList.get(0));
    }

    private static ToDoCommand parseToDoCommand(String args) throws SeikiException {
        checkIfInputIsMissing(args, ERROR_MESSAGE_MISSING_TASK_TITLE, ToDoCommand.COMMAND_HELPER);

        return new ToDoCommand(args);
    }

    private static UnmarkCommand parseUnmarkCommand(String args) throws SeikiException {
        ArrayList<String> argsList = new ArrayList<>(Arrays.asList(args.split(" ")));

        checkIfInputIsEmpty(args, ERROR_MESSAGE_MISSING_TASK_NUMBER, UnmarkCommand.COMMAND_HELPER);
        checkIfInputIsTooLong(argsList, UnmarkCommand.COMMAND_HELPER);

        return new UnmarkCommand(argsList.get(0));
    }
}
