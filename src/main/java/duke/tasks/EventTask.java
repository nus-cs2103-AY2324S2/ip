package duke.tasks;

import java.time.LocalDateTime;

import duke.TaskList;

/**
 * The EventTask class represents a task with an event description and start/end date/time.
 * Inherits from the Task class.
 */
public class EventTask extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an EventTask object with the specified description, start datetime, and end datetime.
     *
     * @param desc the description of the event task
     * @param start the start datetime of the event
     * @param end the end datetime of the event
     */
    public EventTask(String desc, LocalDateTime start, LocalDateTime end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an EventTask object with the specified description, completion status, start datetime,
     *     and end datetime.
     *
     * @param desc the description of the event task
     * @param isDone the completion status of the event task ("1" for done, "0" for not done)
     * @param start the start datetime of the event
     * @param end the end datetime of the event
     */
    public EventTask(String desc, String isDone, LocalDateTime start, LocalDateTime end) {
        super(desc, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the status icon of the event task.
     *
     * @return the status icon of the event task ("[E][X]" if done, "[E][ ]" if not done)
     */
    public String getStatusIcon() {
        return (this.isDone() ? "[E][X] " : "[E][ ] ");
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return a string representation of the event task, including its status icon, description, start datetime,
     *     and end datetime
     */
    public String toString() {
        return this.getStatusIcon() + this.getDesc() + " (from: " + Task.toStringDateTime(this.start)
                + " to: " + Task.toStringDateTime(this.end) + ")";
    }

    /**
     * Returns a string in the CSV format to represent the event task for saving to file.
     *
     * @param tasks this param is not used in this method
     * @return a string in the CSV format representing the event task
     */
    public String save(TaskList tasks) {
        String isDone = this.isDone() ? "1" : "0";
        return "E," + isDone + "," + this.getDesc() + "," + this.start + "," + this.end;
    }
}
