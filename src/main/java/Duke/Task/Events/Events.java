package Duke.Task.Events;

import Duke.Task.Task;

/**
 * This class contains the functions for event tasks.
 * @author Tang Hao Liang
 */
public class Events extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor that updates description and duration for the task.
     *
     * @param description Event's description.
     * @param from Event's Start
     * @param to Event's End
     */
    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "  [E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFile() {
        if (isDone) {
            return "E|1|" + description + "|" + from + "|" + to;
        } else {
            return "E|0|" + description + "|" + from + "|" + to;
        }
    }
}
