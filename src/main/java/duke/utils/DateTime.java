package duke.utils;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class DateTime {
    private final static String printFormat = "MMM dd yyyy HHmm";

    private static DateTimeFormatterBuilder acceptedFormats
            = new DateTimeFormatterBuilder().append(
                    DateTimeFormatter.ofPattern("[dd/MM/yyyy HHmm]"
                            + "[yyyy/MM/dd HHmm]" + "[dd-MM-yyyy HHmm]"
                            + "[yyyy-MM-dd HHmm]" + "[MMM dd yyyy HHmm]"
                    ));

    protected LocalDateTime datetime;

    public DateTime(String datetime) {
        try {
            DateTimeFormatter dateTimeFormatter = acceptedFormats.toFormatter();
            this.datetime = LocalDateTime.parse(datetime, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException(
                    "Wrong date time format! Try the default: 'yyyy-MM-dd HHmm'");
        }
    }

    @Override
    public String toString() {
        return this.datetime.format(DateTimeFormatter.ofPattern(printFormat));
    }
}
