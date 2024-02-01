package aurora.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser that helps to split the command string into chunks that can then be processed by the application.
 */
public class Parser {

    /**
     * Constructor for a Parser.
     */
    public Parser() {

    }

    /**
     * Splits a command string at all spaces
     *
     * @param command Command string.
     * @return The string array containing the split command.
     */
    public static String[] splitAtAllBlanks(String command) {
        return command.split(" ");
    }

    /**
     * Splits a command string into 2 at the first space
     *
     * @param command Command string.
     * @return The string array containing the split command.
     */
    public static String[] splitAtFirstBlank(String command) {
        return command.split(" ", 2);
    }

    /**
     * Splits a command string into 2 at the "/by"
     *
     * @param command Command string.
     * @return The string array containing the split command.
     */
    public static String[] splitAtFirstBy(String command) {
        return command.split(" /by ", 2);
    }

    /**
     * Splits a command string into 2 at the "/from"
     *
     * @param command Command string.
     * @return The string array containing the split command.
     */
    public static String[] splitAtFirstFrom(String command) {
        return command.split(" /from ", 2);
    }

    /**
     * Splits a command string into 2 at the "/to"
     *
     * @param command Command string.
     * @return The string array containing the split command.
     */
    public static String[] splitAtFirstTo(String command) {
        return command.split(" /to ", 2);
    }

    /**
     * Parses a date and time String and returns a LocalDateTime object.
     *
     * @param date String representation of a date.
     * @return LocalDateTime according to the String representation of the date.
     */
    public static LocalDateTime parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateLdt = LocalDateTime.parse(date, formatter);
        return dateLdt;
    }

    /**
     * Parses a date and time String from storage and returns a LocalDateTime object.
     *
     * @param date String representation of a date.
     * @return LocalDateTime according to the String representation of the date.
     */
    public static LocalDateTime parseDateFromStorage(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        LocalDateTime dateLdt = LocalDateTime.parse(date, formatter);
        return dateLdt;
    }

}
