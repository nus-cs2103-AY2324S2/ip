package jade.parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.NoSuchElementException;

import jade.commands.AddCommand;
import jade.commands.Command;
import jade.commands.DeleteCommand;
import jade.commands.ExitCommand;
import jade.commands.FindCommand;
import jade.commands.HelpCommand;
import jade.commands.InvalidCommand;
import jade.commands.ListCommand;
import jade.commands.MarkCommand;
import jade.commands.UnmarkCommand;
import jade.data.Deadline;
import jade.data.Event;
import jade.data.RecurringTask;
import jade.data.Todo;
import jade.exception.JadeException;

/**
 * The <code>Parser</code> object to parse command line input from user.
 */
public class Parser {
    private static final String INVALID_FREQUENCY_MSG = "Please enter a valid frequency of tasks.";
    private static final String INVALID_NUMBER_MSG = "Please input a valid number.";
    private static final String INVALID_DATETIME_MSG = "Your date time format is invalid.";
    private static final String INVALID_DATE_MSG = "Your date format is invalid.";
    private static final String INVALID_TIME_MSG = "Your time format is invalid.";
    private static final String EMPTY_DESCRIPTION_MSG = "Your task description cannot be empty.";
    private static final String INVALID_CMD_LENGTH = "This command length is incorrect, please check help page again";
    private static final String ILLEGAL_ARG_MSG = "Illegal Argument.";

    /**
     * Returns the concatenated string of the descriptions.
     *
     * @param commands Array of commands.
     * @param start    The string where the description starts in the array,
     *                 if empty then starts at 1 by default.
     * @param end      The string where the description ends in the array,
     *                 if empty then ends at the end of the command by default.
     * @return The concatenated description of the task.
     * @throws JadeException If IllegalArgumentException is caught.
     */
    public static String concatStringWithTextBound(String[] commands, String start, String end) throws JadeException {
        try {
            int startIndex = start.isEmpty() ? 1 : Arrays.asList(commands).indexOf(start) + 1;
            int endIndex = end.isEmpty() ? commands.length : Arrays.asList(commands).indexOf(end);
            return String.join(" ", Arrays.copyOfRange(commands, startIndex, endIndex));
        } catch (IllegalArgumentException e) {
            throw new JadeException(ILLEGAL_ARG_MSG);
        }
    }

    /**
     * Checks if the description in the commands is empty,
     * if empty then throws an exception.
     *
     * @param commands Array of commands.
     * @param keyword The first keyword after task description.
     * @throws JadeException If the command length is less than expected length.
     */
    private static void checkEmptyDescription(String[] commands, String keyword) throws JadeException {
        if (keyword.isEmpty()) {
            if (commands.length <= 1) {
                throw new JadeException(EMPTY_DESCRIPTION_MSG);
            }
        }
        if (Arrays.asList(commands).indexOf(keyword) <= 1) {
            throw new JadeException(EMPTY_DESCRIPTION_MSG);
        }
    }
    /**
     * Checks whether the length is correct for fixed-length command.
     * E.g. bye lah will be considered invalid.
     *
     * @param length The expected length of the command.
     * @param commands Array of commands.
     * @throws JadeException If the command length is less than expected length.
     */
    private static void checkCommandLength(int length, String[] commands) throws JadeException {
        if (commands.length != length) {
            throw new JadeException(INVALID_CMD_LENGTH);
        }
    }
    /**
     * Returns a LocalDateTime object by parsing the dateTime string.
     *
     * @param dateTime The dateTime to be parsed.
     * @param pattern The pattern used for parsing.
     * @throws JadeException If DateTimeException is caught.
     */
    public static LocalDateTime parseDateTime(String dateTime, String pattern) throws JadeException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern, Locale.UK)
                    .withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeException e) {
            throw new JadeException(INVALID_DATETIME_MSG);
        }
    }

    /**
     * Returns a LocalDate object by parsing the date string.
     *
     * @param date The date to be parsed.
     * @throws JadeException If DateTimeException is caught.
     */
    public static LocalDate parseDate(String date, String pattern) throws JadeException {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern)
                    .withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeException e) {
            throw new JadeException(INVALID_DATE_MSG);
        }
    }

    /**
     * Returns a LocalTime object by parsing the time string.
     *
     * @param time The time to be parsed.
     * @throws JadeException If DateTimeException is caught.
     */
    public static LocalTime parseTime(String time) throws JadeException {
        try {
            return LocalTime.parse(time, DateTimeFormatter.ofPattern("hh:mm a", Locale.UK));
        } catch (DateTimeException e) {
            throw new JadeException(INVALID_TIME_MSG);
        }
    }

    /**
     * Returns an integer by parsing the string.
     *
     * @param intStr The integer string to be parsed.
     * @throws JadeException If NumberFormatException is caught.
     */
    public static int parseInt(String intStr) throws JadeException {
        try {
            return Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            throw new JadeException(INVALID_NUMBER_MSG);
        }
    }

    /**
     * Returns a Command object by parsing the single line user input.
     *
     * @param str The single line user input.
     */
    public static Command parse(String str) {
        try {
            String[] commands = str.split(" ");
            switch (commands[0]) {
            case "todo":
                return parseTodo(commands);
            case "deadline":
                return parseDeadline(commands);
            case "event":
                return parseEvent(commands);
            case "recur":
                return parseRecur(commands);
            case "list":
                return parseList(commands);
            case "mark":
                return parseMark(commands);
            case "unmark":
                return parseUnmark(commands);
            case "delete":
                return parseDelete(commands);
            case "find":
                return parseFind(commands);
            case "bye":
                return parseBye(commands);
            case "help":
                return parseHelp(commands);
            default:
                assert false : commands[0];
                return parseInvalid();
            }
        } catch (JadeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Returns an AddCommand when the first parsed string is todo.
     */
    private static AddCommand parseTodo(String[] commands) throws JadeException {
        checkEmptyDescription(commands, "");
        return new AddCommand(new Todo(concatStringWithTextBound(commands, "", "")));
    }

    /**
     * Returns an AddCommand when the first parsed string is deadline.
     */
    private static AddCommand parseDeadline(String[] commands) throws JadeException {
        checkEmptyDescription(commands, "/by");
        return new AddCommand(new Deadline(concatStringWithTextBound(commands, "", "/by"),
                parseDateTime(concatStringWithTextBound(commands, "/by", ""), "uuuu-MM-dd hh:mm a")));
    }

    /**
     * Returns an AddCommand when the first parsed string is event.
     */
    private static AddCommand parseEvent(String[] commands) throws JadeException {
        checkEmptyDescription(commands, "/from");
        return new AddCommand(new Event(concatStringWithTextBound(commands, "", "/from"),
                parseDateTime(concatStringWithTextBound(commands, "/from", "/to"), "uuuu-MM-dd hh:mm a"),
                parseDateTime(concatStringWithTextBound(commands, "/to", ""), "uuuu-MM-dd hh:mm a")));
    }

    /**
     * Returns an AddCommand when the first parsed string is recur.
     */
    private static AddCommand parseRecur(String[] commands) throws JadeException {
        try {
            checkEmptyDescription(commands, "/dfrom");
            return new AddCommand(new RecurringTask(concatStringWithTextBound(commands, "", "/dfrom"),
                    parseDate(concatStringWithTextBound(commands, "/dfrom", "/dto"), "uuuu-MM-dd"),
                    parseDate(concatStringWithTextBound(commands, "/dto", "/tfrom"), "uuuu-MM-dd"),
                    parseTime(concatStringWithTextBound(commands, "/tfrom", "/tto")),
                    parseTime(concatStringWithTextBound(commands, "/tto", "/freq")),
                    RecurringTask.TaskFreq.valueOf(commands[commands.length - 1])));
        } catch (NoSuchElementException e) {
            throw new JadeException(INVALID_FREQUENCY_MSG);
        }
    }
    /**
     * Returns a ListCommand when the first parsed string is list.
     */
    private static ListCommand parseList(String[] commands) throws JadeException {
        if (commands.length != 1) {
            return new ListCommand(parseDate(concatStringWithTextBound(commands, "", ""), "uuuu-MM-dd"));
        }
        return new ListCommand();
    }
    /**
     * Returns a MarkCommand when the first parsed string is mark.
     */
    private static MarkCommand parseMark(String[] commands) throws JadeException {
        checkCommandLength(2, commands);
        return new MarkCommand(parseInt(commands[1]));
    }
    /**
     * Returns an UnmarkCommand when the first parsed string is unmark.
     */
    private static UnmarkCommand parseUnmark(String[] commands) throws JadeException {
        checkCommandLength(2, commands);
        return new UnmarkCommand(parseInt(commands[1]));
    }
    /**
     * Returns a DeleteCommand when the first parsed string is delete.
     */
    private static DeleteCommand parseDelete(String[] commands) throws JadeException {
        checkCommandLength(2, commands);
        return new DeleteCommand(parseInt(commands[1]));
    }
    /**
     * Returns a FindCommand when the first parsed string is find.
     */
    private static FindCommand parseFind(String[] commands) throws JadeException {
        checkCommandLength(2, commands);
        return new FindCommand(concatStringWithTextBound(commands, "", ""));
    }
    /**
     * Returns an ExitCommand when the first parsed string is bye.
     */
    private static ExitCommand parseBye(String[] commands) throws JadeException {
        checkCommandLength(1, commands);
        return new ExitCommand();
    }
    /**
     * Returns an ExitCommand when the first parsed string is help.
     */
    private static HelpCommand parseHelp(String[] commands) throws JadeException {
        checkCommandLength(1, commands);
        return new HelpCommand();
    }
    /**
     * Returns an InvalidCommand when the input string is invalid.
     */
    private static InvalidCommand parseInvalid() {
        return new InvalidCommand();
    }
}
