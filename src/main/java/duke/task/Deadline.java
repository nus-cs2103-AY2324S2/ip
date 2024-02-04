package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * Represents a task with a deadline in the Duke chatbot application.
 * It is a subclass of the Task class.
 */
public class Deadline extends Task {

    protected String by;

    private LocalDateTime dueDate;

    /**
     * Constructs a Deadline object with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by           The deadline in the format "yyyy-MM-dd hh:mma".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        builder.parseCaseInsensitive();
        builder.appendPattern("yyyy-MM-dd hh:mma");
        DateTimeFormatter format = builder.toFormatter();
        this.dueDate = LocalDateTime.parse(by, format);
    }

    /**
     * Formats the provided due date using the pattern "MMM dd yyyy 'at' hh:mma".
     *
     * @param dueDate The due date to be formatted.
     * @return The formatted due date string.
     */
    public String formatter(LocalDateTime dueDate) {
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("MMM dd yyyy 'at' hh:mma");
        return customFormat.format(dueDate);
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "D" + " | " + super.toString() + " | " + formatter(this.dueDate);
    }
}
