package fishstock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import fishstock.Command.CommandType;

/**
 * Encapsulates parsing methods.
 */
class Parser {
    protected static final DateTimeFormatter IN_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
    protected static final DateTimeFormatter OUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Check if given input starts with a command.
     * @param command The command.
     * @param input The input to compare.
     * @return The check result.
     */
    protected static boolean startsWith(String command, String input) {
        return input.length() >= command.length() && command.equals(input.substring(0, command.length()));
    }

    /**
     * Parses input into their respective commands.
     * @param input The input that starts with the command.
     * @return The command.
     */
    protected static CommandType parse(String input) {
        for (CommandType commandType : CommandType.values()) {
            if (startsWith(commandType.keyword, input)) {
                return commandType;
            }
        }
        return CommandType.INVALID;
    }

    /**
     * Gets index number from input string.
     * Has the format "[command] [task_number]".
     * Subtracts 1 from task_number to obtain index number for array.
     * @param input The input from user.
     * @return The resulting index number.
     * @throws FishStockException The exceptions while calculating the index number.
     */
    protected static Integer getIndexFromInput(String input) throws FishStockException {
        try {
            String numStr = input.split(" ", 2)[1];
            int num = Integer.parseInt(numStr);
            return num - 1; // Start from index 0

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FishStockException("OH NOSE! Task number cannot be empty..");
        } catch (NumberFormatException e) {
            throw new FishStockException("OH NOSE! Task number has to be an integer..");
        }
    }

    /**
     * Parses date string into LocalDateTime object.
     * Has the format "[dd/mm/yyyy] [hh:mm]".
     * @param date The date string.
     * @return The LocalDateTime object.
     * @throws FishStockException The exceptions while parsing the date.
     */
    protected static LocalDateTime parseDate(String date) throws FishStockException {
        try {
            return LocalDateTime.parse(date, IN_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new FishStockException("OH NOSE! Dates should be of the format <dd/mm/yyyy hh:mm>");
        }
    }

    /**
     * Converts LocalDateTime object into String input format.
     * @param date The LocalDateTime object.
     * @return The String with input format.
     */
    protected static String inDate(LocalDateTime date) {
        return date.format(IN_DATE_FORMAT);
    }

    /**
     * Converts LocalDateTime object into String output format.
     * @param date The LocalDateTime object.
     * @return The String with output format.
     */
    protected static String outDate(LocalDateTime date) {
        return date.format(OUT_DATE_FORMAT);
    }
}
