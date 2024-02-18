package linus.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to read and print dates
 */
public class DateTask {

    private LocalDateTime localDateTime;

    private DateTimeFormatter DATE_TIME_FORMATTER_PRINT = DateTimeFormatter.ofPattern(
                "'Date:' d MMM yyyy 'Time:' h:mm a");
    private DateTimeFormatter DATE_TIME_FORMATTER_SAVE_AND_READ = DateTimeFormatter.ofPattern(
                "dd/MM/yyyy HHmm");

    /**
     * Constructor for DateTask
     * @param localDateTime Local Date Time for the task
     */
    public DateTask(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    /**
     * Constructor for DateTask
     * @param dateString String for date and time
     */
    public DateTask(String dateString) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateString, DATE_TIME_FORMATTER_SAVE_AND_READ);
        this.localDateTime = parsedDateTime;
    }

    /**
     * Get the date for this DateTask
     * @return LocalDateTime of DateTask
     */
    public LocalDateTime getDate() {
        return localDateTime;
    }

    /**
     * Change the date into a save format
     * @return String for the date to be saved into a file
     */
    public String saveFormat() {
        return localDateTime.format(DATE_TIME_FORMATTER_SAVE_AND_READ);
    }

    /**
     * @return String for date to be printed
     */
    @Override
    public String toString() {
        return localDateTime.format(DATE_TIME_FORMATTER_PRINT);
    }
}
