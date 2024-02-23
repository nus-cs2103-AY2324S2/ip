package bytetalker.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents the Event task that the user wants to store. It contains to and from variables to store time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String task, LocalDateTime from, LocalDateTime to) {
        super(TaskType.EVENT, task);
        this.from = from;
        this.to = to;
    }

    public Event(String task, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(TaskType.EVENT, task, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates a string to show information(task type, status, task content, from, to) about the event task.
     *
     * @return String that contains information about task.
     */
    @Override
    public String toString() {
        return "[" + getTaskType().getIcon() + "]" + "[" + getStatusIcon() + "] " + getTask() + " (from: " + convertFromToString() + " to: " + convertToToString() + ")";
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Converts the from variable which is LocalDateTime type to String in set output format(MMM dd yyyy h:mma) to be
     * able to be printed.
     *
     * @return String of from variable.
     */
    public String convertFromToString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma", Locale.ENGLISH);
        String formattedDateTime = this.from.format(outputFormatter);
        return formattedDateTime;
    }

    /**
     * Converts the to variable which is LocalDateTime type to string in set output format(MMM dd yyyy h:mma) to be
     * able to be printed.
     *
     * @return String of to variable.
     */
    public String convertToToString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma", Locale.ENGLISH);
        String formattedDateTime = this.to.format(outputFormatter);
        return formattedDateTime;
    }

    public void updateFrom(LocalDateTime from) {
        this.from = from;
    }

    public void updateTo(LocalDateTime to) {
        this.to = to;
    }
}
