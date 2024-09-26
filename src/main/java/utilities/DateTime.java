package utilities;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A DateTime object that interprets the specified date.
 */
public class DateTime {
    /**
     * The date time string that the user inputs.
     */
    private String dateTime;
    /**
     * The date time object after conversion.
     */
    private LocalDateTime dateTimeData;

    /**
     * DateTime class constructor.
     * @param dateTime The date time string that the user inputs.
     */
    public DateTime(String dateTime) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.dateTime = dateTime;
        this.dateTimeData = LocalDateTime.parse(dateTime, inputFormat);
    }

    /**
     * Formats the date from a date time object into a string.
     * @return The converted date time that is to be output by the program.
     */
    public String formatDate() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm");
        return this.dateTimeData.format(outputFormat);
    }
}
