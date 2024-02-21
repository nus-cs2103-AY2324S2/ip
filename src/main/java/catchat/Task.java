package catchat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Task class handles the tasks of the application
 */
public class Task {
    protected static final DateTimeFormatter DATE_FORMAT_1 = DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH);
    protected static final DateTimeFormatter DATE_FORMAT_2 = DateTimeFormatter.ofPattern("yyyy-M-d", Locale.ENGLISH);
    protected static final DateTimeFormatter DATE_FORMAT_3 = DateTimeFormatter.ofPattern("d-M-yyyy", Locale.ENGLISH);
    protected static final DateTimeFormatter DATE_FORMAT_4 = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
    protected String description;
    protected boolean isDone;


    /**
     * Constructor for Task
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark task as undone
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Check if task is done
     *
     * @return boolean
     */
    public boolean isDone() {
        return this.isDone;
    }

    protected String getTaskTypeString() {
        return "";
    }
    protected TaskType getTaskType() {
        return TaskType.UNKNOWN;
    }

    public String getTaskStatus() {
        return this.isDone ? "done" : "not done";
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Parse the date string and return a LocalDate object
     *
     * @param by
     * @param formatters
     * @return LocalDate
     * @throws IllegalArgumentException
     * @throws Exception
     */
    protected static LocalDate parseDate(String by, DateTimeFormatter... formatters) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(by, formatter);
            } catch (Exception e) {
                // Try the next format
            }
        }
        // If none of the formats match, you may want to handle this case
        throw new IllegalArgumentException(
                "Date could not be parsed with any of the provided formats");
    }
    @Override
    public String toString() {
        return getTaskType() + " | " + getTaskStatus() + " | " + this.description;
    }
}

/**
 * TaskType enum
 */
enum TaskType {
    TODO,
    DEADLINE,
    EVENT,
    UNKNOWN
}
