package duke.task;

import duke.exceptions.DukeException;

/**
 * Class for event tasks
 */
public class EventTask extends Task {

    protected String start_time, end_time;

    /**
     * Constructor for EventTask
     *
     * @param name Name of the event
     * @param start_time Starting time for the task
     * @param end_time Ending time for the task
     * @param file_format File format for the task
     *
     */
    public EventTask(String name, String start_time, String end_time, String file_format) {
        super(name, Task.Type.E, file_format);
        this.start_time = start_time;
        this.end_time = end_time;

    }

    /**
     * String representation of EventTask
     *
     * @return String representation of EventTask
     */
    @Override
    public String toString() {
        String output;
        if (!done) {
            output = "[" + this.type + "]" + "[ ] " + this.name + " (from: " + this.start_time + " to: " + this.end_time + ")\n";
        } else {
            output = "[" + this.type + "]" + "[X] " + this.name + " (from: " + this.start_time + " to: " + this.end_time + ")\n";
        }
        return output;
    }
}
