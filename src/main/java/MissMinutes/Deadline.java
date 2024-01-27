package MissMinutes;

import java.io.Serializable;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;

public class Deadline extends Task implements Serializable {
    protected final LocalDateTime by;
    protected final static Pattern formatter = Pattern.compile("(.+) /by (.+)");

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    public static Deadline fromStr(String input) throws MissMinutesException {
        Matcher matcher = Deadline.formatter.matcher(input);

        if (matcher.find()) {
            String name = matcher.group(1);
            String byStr = matcher.group(2);

            try {
                LocalDateTime by = LocalDateTime.parse(byStr, Task.inputFormat);
                return new Deadline(name, by);
            } catch (DateTimeParseException err) {
                throw new MissMinutesException("Please enter a valid date time format. For example, 2019-12-31 1800");
            }
        } else {
            throw new MissMinutesException("Deadlines have to be created with the following format: deadline <desc> /by <end>");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(Task.outputFormat) + ")";
    }
}
