import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * DateTime class.
 */
public class MyDateTime {
    /**
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

    public static LocalDate convertDate(String input) throws DukeException {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong format for date");
        }
    }

    public static String formatter(LocalDateTime datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd , HH:mm");
        return datetime.format(formatter);
    }

    public static String englishFormatter(LocalDateTime datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return datetime.format(formatter);
    }
}
