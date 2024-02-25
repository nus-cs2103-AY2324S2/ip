package duke;

import java.time.LocalDateTime;

/**
 * Event class represents a task with a time range.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Default constructor, isDone is default.
     *
     * @param description of Event
     * @param from is the start time of event
     * @param to is the end time of event
     */
    public Event(String description, int priority, String from, String to) {
        super(description, priority);
        this.from = from;
        this.to = to;
    }

    /**
     * Overloaded constructor, isDone set.
     *
     * @param description of Event
     * @param isDone completion status of Event
     * @param from is the start time of event
     * @param to is the end time of event
     */
    public Event(String description, Boolean isDone, int priority, String from, String to) {
        super(description, isDone, priority);
        this.from = from;
        this.to = to;
    }

    /**
     * Overloaded constructor with DateTime, isDone defaults
     *
     * @param description of Event
     * @param from is the start time of event
     * @param to is the end time of event
     */
    public Event(String description, int priority, LocalDateTime from, LocalDateTime to) {
        super(description, priority);
        this.from = Dates.dateTime2DbStr(from);
        this.to = Dates.dateTime2DbStr(to);
    }

    /**
     * Constructor used to clone an Event object
     */
    public Event(Event event) {
        super(event);
        this.from = event.from;
        this.to = event.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Converts the database representation of Event to a Event Task.
     *
     * @param dbEvent the string rep of Event in the database
     * @return Task the Event Task object
     */
    public static Event db2Event(String dbEvent) {
        // E | 0 | 1 | project meeting | Aug 6th 2 | 4pm
        String[] params = dbEvent.split(" \\| ");
        Boolean isDone = params[1].equals("1") ? true : false; // if "1", means isDone
        int priority = Integer.parseInt(params[2].trim());
        String desc = params[3];
        String from = params[3];
        String to = params[4];
        return new Event(desc, isDone, priority, from, to);
    }

    /**
     * Converts a Event Task to the database representation of Event.
     *
     * @param eventTask the Event Task object
     * @return Task the string rep of Event in the database
     */
    public static String event2Db(Event eventTask) {
        // E | 0 | 1 | project meeting | Aug 6th 2 | 4pm
        String done = eventTask.isDone ? "1" : "0";
        String priority = Integer.toString(eventTask.priority);
        String desc = eventTask.description;
        String from = eventTask.from;
        String to = eventTask.to;
        return "E" + " | " + done + " | " + priority + " | " + desc + " | " + from + " | " + to;
    }

    public static void main(String[] args) {
        String dbEvent = "E | 0 | 1 | project meeting | Aug 6th 2 | 4pm";
        Event eventTask = Event.db2Event(dbEvent);
        eventTask.markAsDone();
        System.out.println(eventTask);
        System.out.println(Event.event2Db(eventTask));

        // Test creating an event with valid date
        String desc = "Buy Bread";
        Integer priority = 1;
        String validFromDate1 = "15/01/2023 1430";
        String validToDate1 = "17/01/2023 2359";
        if (Dates.isValidInputDate(validFromDate1) && Dates.isValidInputDate(validToDate1)) {
            LocalDateTime validFromDateObj1 = Dates.inputStr2DateTime(validFromDate1);
            LocalDateTime validToDateObj1 = Dates.inputStr2DateTime(validToDate1);
            Event e = new Event(desc, priority, validFromDateObj1, validToDateObj1); // Create date object
            e.markAsDone();
            System.out.println(Event.event2Db(e));
        }
    }
}
