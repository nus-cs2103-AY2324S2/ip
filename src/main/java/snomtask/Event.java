package snomtask;

/**
 * Event implements a task of type event
 * that the user has to attend.
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Creates a new instance of Event.
     * @param name is the name of the Event.
     * @param start is the start date of the event
     *              in yyyy--mm--dd.
     * @param end is the end date of the event
     *            in yyyy-mm--dd.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " from " + this.start + " to " + this.end;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event e = (Event) o;
            boolean isStartDateMatch = this.start.equals(e.start);
            boolean isEndDateMatch = this.end.equals(e.end);
            boolean isDatesMatch = isStartDateMatch && isEndDateMatch;
            boolean isTaskDescMatch = super.equals(e);
            return isTaskDescMatch && isDatesMatch;
        } else {
            return false;
        }
    }
}
