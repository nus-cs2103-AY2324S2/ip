package numerator.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;

/**
 * Represents a Deadline task.
 */
public class Deadline extends numerator.task.Task {
    private final LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and by.
     *
     * @param description should contain information about the task.
     * @param by          should contain information about the deadline.
     * @throws DateTimeParseException if the date and time is not in the correct format.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);

        assert by != null;

        this.by = Task.parseStringToLocalDatetime(by);
    }

    /**
     * Constructs a Deadline task with the specified description, by and tags.
     *
     * @param description should contain information about the task.
     * @param by          should contain information about the deadline.
     * @param tags        should contain information about the tags.
     * @throws DateTimeParseException if the date and time is not in the correct format.
     */
    public Deadline(String description, String by, boolean isDone, Collection<String> tags)
            throws DateTimeParseException {
        super(description, isDone);
        assert by != null;
        this.by = Task.parseStringToLocalDatetime(by);
        super.addTags(tags);
    }


    @Override
    public String toString() {
        return String.format(
                "[D][%s] %s (by: %s) %s",
                this.getStatusIcon(),
                this.description,
                Task.parseLocalDateTimeToString(this.by),
                super.getTagsString()
        ).strip();
    }

    /**
     * Returns a string with task details to be saved in the file.
     *
     * @return a string to be saved in the file.
     */
    @Override
    public String getSaveString() {
        return String.format("D | %d | %s | %s | %s",
                this.isDone ? 1 : 0,
                this.description,
                super.getTagsSaveString(),
                Task.parseLocalDateTimeToString(this.by)
        );
    }
}
