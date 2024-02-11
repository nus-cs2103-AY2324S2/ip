public class EventTask extends Task {
    private String type;
    private String start;
    private String end;

    /**
     * Constructor for Task object of type "event".
     *
     * @param what description of the task
     */
    public EventTask(String what, String start, String end) {
        super(what);
        this.type = "[E]";
        this.start = start;
        this.end = end;
    }

    /**
     * Returns string showing task details.
     *
     * @return string of task type, marked/unmarked status, description, and start and end times.
     */
    public String showAll() {
        return this.type + super.showAll()
                + "(from: " + this.start + " to: " + this.end + ")";
    }
}
