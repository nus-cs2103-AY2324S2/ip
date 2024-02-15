package thecount.task;

import thecount.ui.AddToListReply;
import thecount.ui.Reply;

/**
 * Represents an event task in the to-do list.
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Constructs an event task with the given description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Displays a message related to the event task.
     *
     * @param currSize The current size of the list.
     */
    public String displayMessage(int currSize) {
        Reply replyToUser = new AddToListReply(this.toString(), currSize);
        return replyToUser.displayMessage();
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Gets the description of the task, including the start and end times.
     *
     * @return The description of the task.
     */
    @Override
    public String getDesc() {
        return super.getDesc() + " | " + this.startTime + "-" + this.endTime;
    }

    /**
     * Converts the event task to a string representation.
     *
     * @return The string representation of the event task.
     */
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime
                + " to: " + this.endTime + ")";
    }
}
