package tasks;

import java.time.LocalDateTime;

/**
 * Represents a task of type Event,
 * with task description, completion status, task type,
 * start time and end time as available details.
 */
public class EventTask extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for tasks.EventTask object of type "event".
     *
     * @param what description of task
     * @param status completion status of task
     * @param start start time of task
     * @param end end time of task
     */
    public EventTask(String what, String status, String start, String end) {
        super(what, status, "[E]");
        String[] startArr = start.split(" ");
        String[] endArr = end.split(" ");
        this.start = LocalDateTime.parse(startArr[0] + "T" + startArr[1] + ":00");
        this.end = LocalDateTime.parse(endArr[0] + "T" + endArr[1] + ":00");
    }

    /**
     * Factory method for tasks.EventTask object
     *
     * @param str String array with task details
     * @return tasks.EventTask object with task details in fields
     */
    public static EventTask of(String str) {
        String[] hasWhat = str.split("/", 2);
        String[] hasTimes = hasWhat[1].split("/", 2);
        String[] hasStart = hasTimes[0].split(" ", 2);
        String[] hasEnd = hasTimes[1].split(" ", 2);
        return new EventTask(hasWhat[0], "f", hasStart[1], hasEnd[1]);
    }

    /**
     * Returns string showing task details.
     *
     * @return string of task type, marked/unmarked status, description, and start and end times.
     */
    public String showAll() {
        String[] startArr = this.start.toString().split("T");
        String[] endArr = this.end.toString().split("T");
        return super.showAll()
                + "(from: " + startArr[0] + " " + startArr[1]
                + " to: " + endArr[0] + " " + endArr[1] + ")";
    }

    /**
     * Returns tasks.EventTask details in table row form
     *
     * @return String representation of tasks.EventTask to be saved into txt file
     */
    @Override
    public String toString() {
        String[] startArr = this.start.toString().split("T");
        String[] endArr = this.end.toString().split("T");
        return "E / " + super.toString()
                + " / " + startArr[0] + " " + startArr[1]
                + " / " + endArr[0] + " " + endArr[1];
    }
}
