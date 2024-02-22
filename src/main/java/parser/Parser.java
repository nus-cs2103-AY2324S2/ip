package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import command.Command;
import xiaobai.XiaoBai;

/**
 * Parses user input to create corresponding Command objects.
 */
public class Parser {
    private static final String[] DATE_FORMATS = { "yyyy-MM-dd", "dd-MM-yyyy", "MM-dd-yyyy", "dd/MM/yyyy", "MM/dd/yyyy",
            "yyyy/MM/dd", "dd MMM yyyy", "MMM dd yyyy", "yyyy MMM dd", "dd MMM yyyy", "yyyy-MM-d", "d-MM-yyyy",
            "MM-d-yyyy", "d/MM/yyyy", "MM/d/yyyy", "yyyy/MM/d", "d MMM yyyy", "MMM d yyyy", "yyyy MMM d",
            "d MMM yyyy", "MMM dd yyyy" };
    private static final String[] TIME_FORMATS = { "HH:mm", "HH:mm", "h:mm a", "HHmm", "hh:mm a" };

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param userInput The user input string to be parsed.
     * @return The Command object corresponding to the user input.
     */
    public static Command parseCommand(String userInput) {
        assert userInput != null : "Input string should not be null";
        String[] words = userInput.split(" ");
        String commandWord = words[0].toLowerCase();

        switch (commandWord) {
            case "todo":
                return new Command(words, XiaoBai.CommandType.TODO);
            case "deadline":
                return new Command(words, XiaoBai.CommandType.DEADLINE);
            case "event":
                return new Command(words, XiaoBai.CommandType.EVENT);
            case "list":
                return new Command(words, XiaoBai.CommandType.LIST);
            case "mark":
                return new Command(words, XiaoBai.CommandType.MARK);
            case "unmark":
                return new Command(words, XiaoBai.CommandType.UNMARK);
            case "delete":
                return new Command(words, XiaoBai.CommandType.DELETE);
            case "bye":
                return new Command(words, XiaoBai.CommandType.BYE);
            case "find":
                return new Command(words, XiaoBai.CommandType.FIND);
            default:
                return new Command(words, XiaoBai.CommandType.UNKNOWN);
        }
    }

    /**
     * Converts a date-time string to a LocalDateTime object.
     *
     * @param dateTimeString The date-time string to be converted.
     * @return The LocalDateTime object representing the parsed date-time, or null
     *         if the string cannot be parsed.
     */
    public static LocalDateTime convertDateTime(String dateTimeString) {
        for (String dateFormat : DATE_FORMATS) {
            for (String timeFormat : TIME_FORMATS) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat + " " + dateFormat);
                    return LocalDateTime.parse(dateTimeString, formatter);
                } catch (DateTimeParseException e) {

                }
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat + " " + timeFormat);
                    return LocalDateTime.parse(dateTimeString, formatter);
                } catch (DateTimeParseException e) {

                }
            }
        }
        return null;
    }
}
