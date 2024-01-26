package Tasks;

import CONSTANTS.CORRECT_USAGE;
import CONSTANTS.FORMAT;
import CONSTANTS.EXCEPTIONS;
import Exceptions.SquidDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;


public class Date {
    private String date;
    private LocalDate formattedDate;
    private LocalDateTime formattedDateTime;
    private LocalTime formattedTime;

    private void formatTime(String time) throws SquidDateException {
        System.out.println(time);
        switch (time) {
        case (""):
            break;
        case ("now"):
            this.formattedTime = LocalTime.now();
            break;
        default:
            String[] formats = FORMAT.TIMES;
            LocalTime found = null;
            for (int i = 0; i < formats.length; i++) {
                System.out.println("format" + formats[i]);
                try {
                    found = LocalTime.parse(time, DateTimeFormatter.ofPattern(formats[i], Locale.ENGLISH));
                    System.out.println(found);
                } catch (DateTimeParseException e) {
                    try{
                        found = LocalTime.parse(time);
                    } catch (DateTimeParseException f) {
                    }
                }
            }
            if (found == null) {
                throw new SquidDateException(String.format(EXCEPTIONS.SQUID_DATE, time, "time", CORRECT_USAGE.DATE));
            } else {
                this.formattedTime = found;
            }
        }
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
            String[] formats = FORMAT.DATES;
            LocalDate found = null;
            for (int i = 0; i < formats.length; i++) {
                try {
                    found = LocalDate.parse(date, DateTimeFormatter.ofPattern(formats[i]));
                } catch (DateTimeParseException e) {
                    try{
                        found = LocalDate.parse(date);
                    } catch (DateTimeParseException f) {
                    }
                }
            }
            if (found == null) {
                throw new SquidDateException(String.format(EXCEPTIONS.SQUID_DATE, date, "date", CORRECT_USAGE.DATE));
            } else {
                this.formattedDate = found;
            }
        }
    }
    public Date(String date) throws SquidDateException {
        this.date = date;
        String[] params = this.date.split(",", 2);

        if (params.length > 1) {
            // Take time first.
            formatTime(params[0].toUpperCase().strip());
            formatDate(params[1].toLowerCase().strip());
        } else {
            formatDate(date);
        }
    }

    public String toString() {
//        return this.formattedDate.toString();
        String timeString = "";
        if (this.formattedTime != null) {
            timeString = formattedTime.format(DateTimeFormatter.ofPattern(FORMAT.TIME));
        }
        return timeString + ", " + this.formattedDate.format(DateTimeFormatter.ofPattern(FORMAT.DATE));
    }
}