package squid.Tasks;

import squid.CONSTANTS.CORRECT_USAGE;
import squid.CONSTANTS.FORMAT;
import squid.CONSTANTS.EXCEPTIONS;
import squid.Exceptions.SquidDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Date {
    private String date;
    private LocalDate formattedDate;
    private LocalDateTime formattedDateTime;
    private LocalTime formattedTime;

    private void formatTime(String time) throws SquidDateException {
        switch (time) {
        case (""):
            break;
        case ("now"):
            this.formattedTime = LocalTime.now();
            break;
        default:
            this.formattedTime = parseTime(time);
        }
    }

    private LocalTime parseTime(String time) throws SquidDateException {
        for (int i = 0; i < FORMAT.TIMES.length; i++) {
            try {
                return LocalTime.parse(time, DateTimeFormatter.ofPattern(FORMAT.TIMES[i]));
            } catch (DateTimeParseException e) {
                try{
                    return LocalTime.parse(time);
                } catch (DateTimeParseException f) {
                }
            }
        }
        throw new SquidDateException(String.format(EXCEPTIONS.SQUID_DATE, time, "time", CORRECT_USAGE.DATE));
    }


    private void formatDate(String date) throws SquidDateException {
        switch (date) {
        case ("today"):
            this.formattedDate = LocalDate.now();
            break;
        case ("tmr"):
            // Fallthrough
        case ("tomorrow"):
            this.formattedDate = LocalDate.now().plusDays(1);
            break;
        default:
            this.formattedDate = parseDate(date);
        }
    }

    private LocalDate parseDate(String date) throws SquidDateException {
        for (int i = 0; i < FORMAT.DATES.length; i++) {
            try {
                return LocalDate.parse(date, DateTimeFormatter.ofPattern(FORMAT.DATES[i]));
            } catch (DateTimeParseException e) {
                try{
                    return LocalDate.parse(date);
                } catch (DateTimeParseException f) {
                }
            }
        }
        throw new SquidDateException(String.format(EXCEPTIONS.SQUID_DATE, date, "date", CORRECT_USAGE.DATE));
    }


    public Date(String date) throws SquidDateException {
        this.date = date;
        String[] params = this.date.split(",", 2);

        if (params.length > 1) {
            // Take time first.
            formatTime(params[0].toUpperCase().strip());
            formatDate(params[1].strip());
        } else {
            formatDate(date);
        }
    }

    public String toString() {
        String timeString = "";
        if (this.formattedTime != null) {
            timeString = formattedTime.format(DateTimeFormatter.ofPattern(FORMAT.TIME)) + ", ";
        }
        return timeString + this.formattedDate.format(DateTimeFormatter.ofPattern(FORMAT.DATE));
    }
}