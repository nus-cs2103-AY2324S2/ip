package duke.others;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Converts dates to more readable form.
 */
public class DateFormatter {
    private String date;

    /**
     * Constructs DateFormatter.
     *
     * @param date to convert.
     */
    public DateFormatter(String date) {
        this.date = date;
    }

    /**
     * Returns true if date is in valid format, else
     * returns false.
     *
     * @return Boolean indicating if it is in a
     *     correct format.
     */
    public Boolean isValidDate() {
        try {
            LocalDate currdate = LocalDate.parse(this.date);
            return true;
        } catch (DateTimeException e) {
            // don't change the date in this case
            // as it is not a valid date, return false.
        }
        return false;
    }

    /**
     * Returns date converted into a more
     * readable format.
     */
    public String convertDate() {
        LocalDate currdate = LocalDate.parse(this.date);
        return currdate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
