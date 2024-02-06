package duke.dataprocessing;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    public static LocalDate parse(String date) throws DukeException {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            if (parsedDate.isBefore(LocalDate.now())) {
                throw new DukeException("OOPS!!! The date you provided is already passed.");
            }
            return parsedDate;
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! deadline should be in the format: yyyy-mm-dd.\n" +
                    "Please check if your date is valid");
        }
    }
}
