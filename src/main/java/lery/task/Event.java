package lery.task;

/**
 * Represents an event task in the Lery chatbot application,
 * which is a specific type of task associated with a start and end time.
 *
 * It extends the generic Task class and provides methods to get the task type,
 * extra information (shortened and full), and description. Additionally, it overrides
 * the getType, getExtraInfoShortened, and getExtraInfo methods to customize the behavior
 * for an event task.
 */
public class Event extends Task {
    private static final String TYPE = "E";
    private String start;
    private String end;

    /**
     * Constructs an Event object with the specified description, extracting
     * the start and end times from the description.
     *
     * @param description the description of the event task.
     * @param isDone      the completion status of the task (true if done, false otherwise).
     */
    public Event(String description, boolean isDone) {
        super(description.split("/")[0], isDone);
        int fromId = description.indexOf("/from");
        int toId = description.indexOf("/to");
        this.start = description.substring(fromId + 6, toId - 1);
        this.end = description.substring(toId + 4);
    }

    /**
     * Constructs an Event object with the specified event and extra information.
     *
     * @param event      the event description of the event task.
     * @param extraInfo  the extra information containing the start and end times.
     * @param isDone      the completion status of the task (true if done, false otherwise).
     */
    public Event(String event, String extraInfo, boolean isDone) {
        super(event, isDone);
        String[] x = extraInfo.split("-");
        this.start = x[0];
        this.end = x[1];
    }

    /**
     * Gets the type of the event task.
     *
     * @return the task type ("E" for event).
     */
    @Override
    public String getType() {
        return this.TYPE;
    }

    /**
     * Gets a shortened version of extra information.
     *
     * @return a string containing the shortened form of the extra information.
     */
    @Override
    public String getExtraInfoShortened() {

        return this.start + "-" + this.end;
    }

    /**
     * Gets the extra information of the event task.
     *
     * @return a string containing the extra information.
     */
    @Override
    public String getExtraInfo() {
        return " (from: " + this.start + " to: " + this.end + ")";
    }
}
