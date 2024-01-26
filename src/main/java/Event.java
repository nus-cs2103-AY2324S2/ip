public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Default constructor, isDone set to false
     * @param description of Event
     * @param from is the start time of event
     * @param to is the end time of event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, Boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Converts the database representation of Event to a Event Task
     * @param dbEvent the string rep of Event in the database
     * @return Task the Event Task object
     */
    public static Event db2Event(String dbEvent) {
        // E | 0 | project meeting | Aug 6th 2 | 4pm
        String[] params = dbEvent.split(" \\| ");
        Boolean isDone = params[1].equals("1") ? true : false; // if "1", means isDone
        String desc = params[2];
        String from = params[3];
        String to = params[4];
        return new Event(desc, isDone, from, to);
    }

    /**
     * Converts a Event Task to the database representation of Event
     * @param eventTask the Event Task object
     * @return Task the string rep of Event in the database
     */
    public static String event2Db(Event eventTask) {
        // E | 0 | project meeting | Aug 6th 2 | 4pm
        String done = eventTask.isDone ? "1" : "0";
        String desc = eventTask.description;
        String from = eventTask.from;
        String to = eventTask.to;
        return "E" + " | " + done + " | " + desc + " | " + from + " | " + to;
    }

    public static void main(String[] args) {
        String dbEvent = "E | 0 | project meeting | Aug 6th 2 | 4pm";
        Event eventTask = Event.db2Event(dbEvent);
        eventTask.markAsDone();
        System.out.println(eventTask);
        System.out.println(Event.event2Db(eventTask));
    }
}
