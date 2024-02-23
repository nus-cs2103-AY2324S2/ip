package duke;

import java.time.LocalDateTime;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected LocalDateTime startingTime, endingTime;

    public Event(String description, LocalDateTime startingTime, LocalDateTime endingTime) {
        super(description);
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    @Override
    public boolean isClashingWith(Task otherTask) {
        if (otherTask instanceof Event) {
            Event otherEvent = (Event) otherTask;
            return this.startingTime.isBefore(otherEvent.endingTime) && this.endingTime.isAfter(otherEvent.startingTime);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startingTime.format(Constants.OUTPUT_FORMATTER)
            + " to: " + endingTime.format(Constants.OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String serializeToCommand(int taskIndex) {
        return "event " + description + " /from " + startingTime.format(Constants.INPUT_FORMATTER)
            + " /to " + endingTime.format(Constants.INPUT_FORMATTER) + "\n" + serializeDoneMark(taskIndex);
    }
}
