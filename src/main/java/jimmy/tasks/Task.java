package jimmy.tasks;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task template.
 */
public abstract class Task implements Serializable {
    protected final String desc;
    // this date format displays the date purely in numbers
    protected final DateTimeFormatter informalDateFormat = DateTimeFormatter.ofPattern("d-MM-yyyy");
    // this date format displays the month in words
    protected final DateTimeFormatter formalDateFormat = DateTimeFormatter.ofPattern("d MMM yyyy");
    private boolean isCompleted;

    /**
     * Constructor for Task class.
     *
     * @param taskName Name of the task.
     */
    public Task(String taskName, boolean isCompleted) {
        this.desc = taskName;
        this.isCompleted = isCompleted;
    }

    public abstract String toFileString();

    /**
     * Marks the task as complete.
     */
    public void markAsComplete() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsIncomplete() {
        this.isCompleted = false;

    }

    /**
     * Returns the status of the task.
     *
     * @return "X" if the task is completed, " " otherwise.
     */
    public String getStatus() {
        return this.isCompleted
                ? "X"
                : " ";
    }

    public String getDesc() {
        return this.desc;
    }

    /**
     * Converts date given by the user to LocalDate object.
     *
     * @param dateString Date given by the user.
     * @return Date given by the user as LocalDate object.
     */
    protected LocalDate parseStringtoLocalDate(String dateString) throws DateTimeParseException {
        return LocalDate.parse(dateString, informalDateFormat);
    }

    /**
     * Converts LocalDate object to String.
     *
     * @param localDate LocalDate object.
     * @return String representation of the LocalDate object.
     */
    protected String parseLocalDatetoString(LocalDate localDate) {
        return localDate.format(formalDateFormat);
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.desc;
    }
}
