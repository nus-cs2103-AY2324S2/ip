package friendlytool.process;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for managing date used for tasks.
 */
public class Date {
    private LocalDateTime date;
    private String dateStr;


    /**
     * Constructs the Date Object.
     *
     * @param date date provided by the user.
     */
    public Date(String date) {
        this.dateStr = date;
        this.date = LocalDateTime.parse(date.trim());
    }

    /**
     * Changes date's format into a readable format.
     *
     * @return String representing date.
     */
    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Provides date format for saving.
     *
     * @return string format of date.
     */
    public String toSaveFormat() {
        return dateStr;
    }


    public int compareTo(Date another) {
        return this.date.compareTo(another.date);
    }
}
