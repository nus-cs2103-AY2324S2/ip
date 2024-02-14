package duke.helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * DateTime class.
 */
public class MyDateTime {
    /**
     * Returns LocalDateTime of the input date time.
     * Converts String datetime to LocalDateTime.
     *
     * @param input String representation of datetime.
     * @return LocalDateTime.
     */
    public static LocalDateTime convertDateTime(String input) throws DukeException {
        try {
            String[] datetime = input.split(" ");
            LocalDate date = LocalDate.parse(datetime[0]);
            LocalTime time = LocalTime.parse(datetime[1]);
            LocalDateTime output = LocalDateTime.of(date, time);

            return output;
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong format for date and time!");
        }
    }

    /**
     * Returns a LocalDate of a specific date.
     *
     * @param input String of date.
     * @return LocalDate of the date.
     * @throws DukeException
     */
    public static LocalDate convertDate(String input) throws DukeException {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong format for date");
        }
    }

    /**
     * Returns String output of datetime representation in yyyy-MM-dd , HH:mm.
     *
     * @param datetime
     * @return String output of datetime representation in yyyy-MM-dd , HH:mm.
     */
    public static String formatter(LocalDateTime datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd , HH:mm");
        return datetime.format(formatter);
    }

    /**
     * Returns String output of datetime representation in MMM dd, yyyy HH:mm.
     *
     * @param datetime
     * @return String output of datetime representation in MMM dd, yyyy HH:mm.
     */
    public static String englishFormatter(LocalDateTime datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return datetime.format(formatter);
    }
}
