package dave.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    /** The format of the input. */
    public static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    /** The format of the output. */
    public static final DateTimeFormatter FORMATTER_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy ha");

    /**
     * Get the current system time.
     * 
     * @return The current system time.
     */
    public static LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    /**
     * Get the time a week later from now.
     * 
     * @return The system time a week later from now.
     */
    public static LocalDateTime getAWeekFromNow() {
        return LocalDateTime.now().plusWeeks(1);
    }

    /**
     * Format the current time.
     * 
     * @return The formatted string of the current time.
     */
    public static String getCurrentTimeAsString() {
        return getCurrentTime().format(FORMATTER_OUTPUT);
    }

    /**
     * Format the time a week later from now.
     * 
     * @return The formatted string of the time a week later from now.
     */
    public static String getAWeekFromNowAsString() {
        return getAWeekFromNow().format(FORMATTER_OUTPUT);
    }

    /**
     * Checks if a given due date for a task is within a week from now.
     * 
     * @param taskDate The due date of a task.
     * @return True if the due date of a task is within a week from now, false
     *         otherwise.
     */
    public static boolean isWithinAWeekFromNow(LocalDateTime taskDate) {
        int dayOfYearNow = getCurrentTime().getDayOfYear();
        int dayOfYearAWeekLater = getAWeekFromNow().getDayOfYear();
        if (taskDate.getYear() == getCurrentTime().getYear()) {
            int dayOfYearOfTask = taskDate.getDayOfYear();
            if (dayOfYearOfTask >= dayOfYearNow && dayOfYearOfTask <= dayOfYearAWeekLater) {
                return true;
            }
        }
        return false;
    }
}
