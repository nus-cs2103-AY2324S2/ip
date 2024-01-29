package chatbot;

import chatbot.exceptions.InvalidArgumentException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

class DeadlineTask extends Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDateTime deadlineTime;

    public DeadlineTask(String desc, String deadlineTime) throws InvalidArgumentException {
        super(desc);
        try {
            this.deadlineTime = parseDate(deadlineTime);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException();
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadlineTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }

    private static LocalDateTime parseDate(String date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern(
                        "[yyyy-MM-dd HH:mm]" +
                        "[yyyy-MM-dd]" +
                        "[yyyy-MM]"
                ))
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();

        return LocalDateTime.parse(date, formatter);
    }
}
