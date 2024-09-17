package chatbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;

import chatbot.exceptions.InvalidArgumentException;
import chatbot.exceptions.InvalidCommandException;

/**
 * Represents a utility class to parse string inputs from the user.
 */
public class Parser {
    private static final Map<String, Command> cmdStrMap = new HashMap<>();

    static {
        for (Command cmd : Command.values()) {
            cmdStrMap.put(cmd.toString(), cmd);
        }
    }

    /**
     * Parses a string to extract the relevant command.
     *
     * @param rep The input string.
     * @return The command.
     * @throws InvalidCommandException If the string does not represent a valid command.
     */
    public static Command toCommand(String rep) throws InvalidCommandException, InvalidArgumentException {
        String[] split = rep.split("\\s+", 2);
        Command cmd = cmdStrMap.get(split[0]);
        if (cmd == null) {
            throw new InvalidCommandException();
        }
        if (split.length > 1 && cmd.hasArgs()) {
            cmd.withArgs(split[1]);
        }
        if (split.length > 1 && !cmd.hasArgs()) {
            throw new InvalidArgumentException();
        }
        return cmd;
    }

    /**
     * Parses a string to extract the date.
     *
     * @param date The date string.
     * @return The date as LocalDateTime.
     */
    public static LocalDateTime parseDate(String date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern(
                        "[yyyy-MM-dd HH:mm]"
                        + "[yyyy-MM-dd]"
                        + "[yyyy-MM]"
                ))
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();

        return LocalDateTime.parse(date, formatter);
    }
}
