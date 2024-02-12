public class EventTask extends Task {
    private String type;
    private String start;
    private String end;

    /**
     * Constructor for Task object of type "event".
     *
     * @param what description of the task
     */
    private EventTask(String what, String start, String end) {
        super(what);
        this.type = "[E]";
        this.start = start;
        this.end = end;
    }

    /**
     * Factory method for EventTask object
     *
     * @param arr String array with task details
     * @return EventTask object with task details in fields
     */
    public static EventTask of(String[] arr) {
        String[] hasWhat = arr[1].split("/", 2);
        String[] hasTimes = hasWhat[1].split("/", 2);
        String[] hasStart = hasTimes[0].split(" ", 2);
        String[] hasEnd = hasTimes[1].split(" ", 2);
        return new EventTask(hasWhat[0], hasStart[1], hasEnd[1]);
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
