package duke.utils;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class DateTime {
    private final static String PRINT_DATE_TIME_FORMAT = "MMM dd yyyy HHmm";
    private final static String PRINT_DATE_FORMAT = "MMM dd yyyy";

    private static DateTimeFormatterBuilder acceptedDateTimeFormats =
            new DateTimeFormatterBuilder().append(
                    DateTimeFormatter.ofPattern("[dd/MM/yyyy HHmm]"
                            + "[yyyy/MM/dd HHmm]" + "[dd-MM-yyyy HHmm]"
                            + "[yyyy-MM-dd HHmm]" + "[MMM dd yyyy HHmm]"
                    ));

    private static DateTimeFormatterBuilder acceptedDateFormats
            = new DateTimeFormatterBuilder().append(
            DateTimeFormatter.ofPattern("[dd/MM/yyyy]"
                    + "[yyyy/MM/dd]" + "[dd-MM-yyyy]"
                    + "[yyyy-MM-dd]" + "[MMM dd yyyy]"
            ));

    protected LocalDateTime datetime;
    protected LocalDate date;

    public DateTime(String datetime) {
        String[] currInput = datetime.split(" ", 4);

        // input with no time
        if (currInput.length == 1 || currInput.length == 3) {
            try {
                DateTimeFormatter dateTimeFormatter = acceptedDateFormats.toFormatter();
                this.date = LocalDate.parse(datetime, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                throw new DukeException(
                        "wrong date format! Try the default: 'dd-MM-yyyy'");
            }
        // input with time
        } else if ((currInput.length == 2) || (currInput.length == 4)) {
            try {
                DateTimeFormatter dateTimeFormatter = acceptedDateTimeFormats.toFormatter();
                this.datetime = LocalDateTime.parse(datetime, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                throw new DukeException(
                        "wrong date time format! Try the default: 'dd-MM-yyyy HHmm'");
            }
        }
    }

    @Override
    public String toString() {
        try {
            if (datetime != null) {
                return this.datetime.format(DateTimeFormatter.ofPattern(PRINT_DATE_TIME_FORMAT));
            } else {
                return this.date.format(DateTimeFormatter.ofPattern(PRINT_DATE_FORMAT));
            }
        } catch (NullPointerException e) {
            throw new DukeException("unable to print date!");
        }
    }
}
