package lucky.tasks;

import java.time.LocalDateTime;

import lucky.common.Utils;

/**
 * The Event class represents an event task with a start and end time.
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Defines a constructor for the `Event` class.
     *
     * @param description Description of the Event task.
     * @param from The starting date of the task.
     * @param to The ending date of the task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts an event object into a string representation for storage, including its status,
     * description, and time range.
     *
     * @return A string that represents the object's data in a storage-friendly format.
     */
    @Override
    public String toStorageString() {
        int statusValue = this.getIsMarked() ? 1 : 0;
        assert statusValue == 0 || statusValue == 1 : "Status value must be equal to 0 or 1";
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("event~%d~%s~%s~%s", statusValue, this.description,
                Utils.inputFormat(this.from), Utils.inputFormat(this.to)));

        for (String tag : this.getTags()) {
            sb.append("~").append(tag);
        }

        return sb.toString();
    }

    /**
     * Returns a string representation of an Event with its status and description.
     *
     * @return A string representation of a task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Utils.dateTimeToString(this.from)
                + " to: " + Utils.dateTimeToString(this.to) + ")";
    }
}
