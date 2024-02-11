package jade.parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import jade.commands.AddCommand;
import jade.commands.Command;
import jade.commands.DeleteCommand;
import jade.commands.ExitCommand;
import jade.commands.FindCommand;
import jade.commands.InvalidCommand;
import jade.commands.ListCommand;
import jade.commands.MarkCommand;
import jade.commands.UnmarkCommand;
import jade.data.Deadline;
import jade.data.Event;
import jade.data.Todo;
import jade.exception.JadeException;

/**
 * The <code>Parser</code> object to parse command line input from user.
 */
public class Parser {

    /**
     * Returns the concatenated string of the descriptions.
     *
     * @param commands Array of commands.
     * @param start The string where the description starts in the array,
     *              if empty then starts at 1 by default.
     * @param end The string where the description ends in the array,
     *            if empty then ends at the end of the command by default.
     * @return The concatenated description of the task.
     * @throws JadeException If IllegalArgumentException is caught.
     */
    public static String concatStringWithTextBound(String[] commands, String start, String end) throws JadeException {
        try {
            int startIndex = start.isEmpty() ? 1 : Arrays.asList(commands).indexOf(start) + 1;
            int endIndex = end.isEmpty() ? commands.length : Arrays.asList(commands).indexOf(end);
            return String.join(" ", Arrays.copyOfRange(commands, startIndex, endIndex));
        } catch (IllegalArgumentException e) {
            throw new JadeException("Illegal Argument");
        }
    }

    /**
     * Checks if the description in the commands is empty,
     * if empty then throws an exception.
     *
     * @param commands Array of commands.
     * @param length The expected length of the command array.
     * @throws JadeException If the command length is less than expected length.
     */
    public static void checkEmptyDescription(int length, String... commands) throws JadeException {
        if (commands.length < length) {
            throw new JadeException("Your task description cannot be empty!");
        }
    }

    /**
     * Returns a LocalDateTime object by parsing the dateTime string.
     *
     * @param dateTime The dateTime to be parsed.
     * @throws JadeException If DateTimeException is caught.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws JadeException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd hmma"));
        } catch (DateTimeException e) {
            throw new JadeException("Your date format is invalid!");
        }
    }

    /**
     * Returns a LocalDate object by parsing the date string.
     *
     * @param date The date to be parsed.
     * @throws JadeException If DateTimeException is caught.
     */
    public static LocalDate parseDate(String date) throws JadeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeException e) {
            throw new JadeException("Your date format is invalid!");
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
            throw new JadeException("Please input a valid number!");
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
                checkEmptyDescription(2, commands);
                return new AddCommand(new Todo(concatStringWithTextBound(commands, "", "")));
            case "deadline":
                checkEmptyDescription(5, commands);
                return new AddCommand(new Deadline(concatStringWithTextBound(commands, "", "/by"),
                        parseDateTime(concatStringWithTextBound(commands, "/by", ""))));
            case "event":
                checkEmptyDescription(8, commands);
                return new AddCommand(new Event(concatStringWithTextBound(commands, "", "/from"),
                        parseDateTime(concatStringWithTextBound(commands, "/from", "/to")),
                        parseDateTime(concatStringWithTextBound(commands, "/to", ""))));
            case "list":
                if (commands.length != 1) {
                    return new ListCommand(parseDate(concatStringWithTextBound(commands, "", "")));
                }
                return new ListCommand();
            case "mark":
                checkEmptyDescription(2, commands);
                return new MarkCommand(parseInt(commands[1]));
            case "unmark":
                checkEmptyDescription(2, commands);
                return new UnmarkCommand(parseInt(commands[1]));
            case "delete":
                checkEmptyDescription(2, commands);
                return new DeleteCommand(parseInt(commands[1]));
            case "find":
                checkEmptyDescription(2, commands);
                return new FindCommand(concatStringWithTextBound(commands, "", ""));
            case "bye":
                checkEmptyDescription(1, commands);
                return new ExitCommand();
            default:
                assert false : commands[0];
                return new InvalidCommand();
            }
        } catch (JadeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
