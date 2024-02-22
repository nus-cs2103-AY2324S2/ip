package duke.task;

import duke.utils.DateTime;

/**
 * The Event class represents a task with a specified event period in the Duke chatbot.
 * It extends the Task class and includes information about the event period.
 */
public class Event extends Task {
    protected DateTime from;
    protected DateTime to;

    /**
     * Constructs an Event task with the given description and event period.
     *
     * @param description The description of the task.
     * @param from The starting date and time of the event in string format.
     * @param to The ending date and time of the event in string format.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = new DateTime(from);
        this.to = new DateTime(to);
        assert (this.from != null && this.to != null) : "'From' and 'to' should not be null!";
    }

    /**
     * Generates the formatted content of the Event task for storage in a file.
     *
     * @return The formatted content of the Event task.
     */
    @Override
    public String writeContent() {
        return "E |" + (this.isDone ? " 1 | " : " 0 | ") + this.getDescription()
                + " | " + this.from + " | " + this.to;
    }

    /**
     * Generates a string representation of the Event task to display.
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        Event other = (Event) o;
        return other.description.equals(this.description)
                && other.from.equals(this.from)
                && other.to.equals(this.to);
    }
}
