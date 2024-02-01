package numerator.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task
 */
public abstract class Task {
    final String description;
    protected boolean isDone;


    /**
     * Constructs a task with the specified description
     *
     * @param description should contain information about the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Parses a string in the format of "yyyy/MM/dd HH:mm" or "yyyy/MM/dd" to a LocalDateTime object
     *
     * @param datetimeString a string in the format of "yyyy/MM/dd HH:mm" or "yyyy/MM/dd"
     * @return a LocalDateTime object
     * @throws DateTimeParseException if the date and time is not in the correct format
     */
    static LocalDateTime parseStringToLocalDatetime(String datetimeString) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        try {
            return LocalDateTime.parse(datetimeString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(datetimeString + " 00:00", dateTimeFormatter);
        }
    }

    /**
     * Parses a LocalDateTime object to a string in the format of "yyyy/MM/dd HH:mm"
     *
     * @param localDateTime a LocalDateTime object
     * @return a string in the format of "yyyy/MM/dd HH:mm"
     * @throws DateTimeException if the date and time is not in the correct format
     */
    static String parseLocalDateTimeToString(LocalDateTime localDateTime) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return localDateTime.format(formatter);
    }


    /**
     * Returns a "X" (done) or " " *undone) for the status of the task.
     *
     * @return "X" or " " representing the status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string with task details to be saved in the file
     *
     * @return a string to be saved in the file
     */
    public abstract String getSaveString();

}
