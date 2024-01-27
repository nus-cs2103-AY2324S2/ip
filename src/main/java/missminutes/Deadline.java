package missminutes;

import java.io.Serializable;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;

/**
 * Represents a more specific task, with a deadline (stored as a LocalDateTime)
 *
 * @implements Serializable to be stored in binary format files for persistence
 */
public class Deadline extends Task implements Serializable {
    protected final LocalDateTime by;
    protected final static Pattern FORMATTER = Pattern.compile("(.+) /by (.+)");

    /**
     * Creates a new Deadline object with the given name and deadline
     *
     * @param name The name of the Deadline object
     * @param by The deadline to be finished by
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Creates a new Deadline object from the user input (string)
     *
     * @param input The string representation of the Deadline object
     * @return The Deadline object deserialized from the string
     * @throws MissMinutesException If the given string is in incorrect format
     */
    public static Deadline fromStr(String input) throws MissMinutesException {
        Matcher matcher = Deadline.FORMATTER.matcher(input);

        if (matcher.find()) {
            String name = matcher.group(1);
            String byStr = matcher.group(2);

            try {
                LocalDateTime by = LocalDateTime.parse(byStr, Task.INPUT_DATE_FORMAT);
                return new Deadline(name, by);
            } catch (DateTimeParseException err) {
                throw new MissMinutesException("Please enter a valid date time format. For example, 2019-12-31 1800");
            }
        } else {
            throw new MissMinutesException("Deadlines have to be created with the following format: deadline <desc> /by <end>");
        }
    }

    /**
     * Returns the string representation of the Deadline object to be printed
     *
     * @return The string representation
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(Task.OUTPUT_DATE_FORMAT) + ")";
    }
}
