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
     * Parses command type from input.
     * @param commandStr The string command.
     * @return The command type.
     */
    protected static CommandType parseCommandType(String commandStr) {
        for (CommandType commandType : CommandType.values()) {
            if (commandStr.equals(commandType.keyword)) {
                return commandType;
            }
        }
        return CommandType.INVALID;
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
