package fredricksen.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import fredricksen.exceptions.FredricksenException;

/**
 * Represents a Task class with the user input task, the Task type,
 * and the status of the task represented by boolean values. It serves
 * as a base class for different task types such as Deadline, Event and ToDo.
 */
public class Task {
    private String task;
    private String type;
    private boolean isDone;

    /**
     * Constructs a Task instance with the specified task description,
     * the task type, and the status of the task isDone, represented by boolean values.
     *
     * @param task The task description as per user input.
     * @param type The task type.
     * @param isDone The status of the task isDone, represented by boolean values.
     */
    public Task(String task, String type, boolean isDone) {
        this.task = task;
        this.type = type;
        this.isDone = isDone;
    }

    /**
     * Set the status isDone of the Task instance.
     */
    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Get the status of the Task instance.
     *
     * @return A boolean isDone of the status of the task.
     */
    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Get the task type of the Task instance.
     *
     * @return A String of the type of the task.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Get the Task type task.
     * @return The instance's task.
     */
    public String getTask() {
        return this.task;
    }

    public DateTimeFormatter[] getDateTimeFormats() {
        return new DateTimeFormatter[]{
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("d-M-yyyy"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
        };
    }

    /**
     * Format the date or date time based on the String date
     * that is passed into the function.
     *
     * @param date The String date or date time to be formatted
     * @return A String with the formatted date or date time.
     * @throws FredricksenException throws an Exception if both formatting methods does not work.
     */
    public String formatDates(String date) throws FredricksenException {
        String result = "";
        DateTimeFormatter[] formats = this.getDateTimeFormats();

        for (DateTimeFormatter format : formats) {
            try {
                LocalDateTime time = LocalDateTime.parse(date, format);
                result = time.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a"));
                return result;
            } catch (DateTimeParseException ignored) {
                // Try the next formatter if parsing fails
            }
        }

        for (DateTimeFormatter format : formats) {
            try {
                LocalDate dateWoTime = LocalDate.parse(date, format);
                result = dateWoTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                return result;
            } catch (DateTimeParseException ignored) {
                // Try the next formatter if parsing fails
            }
        }

        throw new FredricksenException();
    }

    @Override
    public String toString() {
        return "[" + this.type + "][" + (this.isDone ? "X" : "") + "] ";
    }
}
