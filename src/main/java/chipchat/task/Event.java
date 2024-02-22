package chipchat.task;

import java.time.LocalDate;
import java.util.List;

import chipchat.action.CommandType;

/**
 * Represents a type of task that is an event. Subclass of task.
 */
public class Event extends Task {
    private final LocalDate dateFrom;
    private final LocalDate dateTo;

    /**
     * Constructor of Event. Takes in the starting date and ending date.
     *
     * @param description name/description of the task
     * @param isDone completion status of the task
     * @param dateFrom starting date
     * @param dateTo ending date
     */
    public Event(String description, boolean isDone, LocalDate dateFrom, LocalDate dateTo, List<String> tags) {
        super(description, isDone, tags);
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s) %s",
                super.toString(), this.dateFrom, this.dateTo, super.printTags());
    }

    /**
     * Returns the data-format string of the task. For Chipchat-specific storage purposes.
     *
     * @return a string representation of the task in a parsable format
     */
    @Override
    public String dataString() {
        return String.format("%s /isDone %s /from %s /to %s",
                CommandType.EVENT, super.dataString(), this.dateFrom, this.dateTo);
    }
}
