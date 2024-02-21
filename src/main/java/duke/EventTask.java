package duke;

import java.time.LocalDateTime;

/**
 * Represents a task with a specific time range.
 */
public class EventTask extends Task {

    protected String startTime;
    protected String endTime;

    /**
     * Default constructor with completion status set to false.
     *
     * @param description description of the event
     * @param startTime   the start time of the event
     * @param endTime     the end time of the event
     */
    public EventTask(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Overloaded constructor allowing setting of completion status.
     *
     * @param description description of the event
     * @param isDone      completion status of the event
     * @param startTime   the start time of the event
     * @param endTime     the end time of the event
     */
    public EventTask(String description, Boolean isDone, String startTime, String endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Overloaded constructor for creating an event task with LocalDateTime start and end time.
     *
     * @param description description of the event
     * @param startTime   the start time of the event
     * @param endTime     the end time of the event
     */
    public EventTask(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = DateHandler.dateTimeToDatabaseString(startTime);
        this.endTime = DateHandler.dateTimeToDatabaseString(endTime);
    }

    /**
     * Copy constructor for cloning an EventTask object.
     */
    public EventTask(EventTask event) {
        super(event);
        this.startTime = event.startTime;
        this.endTime = event.endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

    /**
     * Converts the database representation of an event task to an EventTask object.
     *
     * @param dbEvent the string representation of the event task in the database
     * @return EventTask the EventTask object
     */
    public static EventTask dbToEventTask(String dbEvent) {
        String[] para = dbEvent.split(" \\| ");
        Boolean isDone = para[1].equals("1") ? true : false;
        String desc = para[2];
        String startTime = para[3];
        String endTime = para[4];
        return new EventTask(desc, isDone, startTime, endTime);
    }

    /**
     * Converts an EventTask object to its database representation.
     *
     * @param eventTask the EventTask object
     * @return String the database representation of the EventTask
     */
    public static String eventTaskToDb(EventTask eventTask) {
        String done = eventTask.isTaskDone ? "1" : "0";
        String description = eventTask.taskDescription;
        String startTime = eventTask.startTime;
        String endTime = eventTask.endTime;
        return "E" + " | " + done + " | " + description + " | " + startTime + " | " + endTime;
    }

    public static void main(String[] args) {
        String dbEvent = "E | 0 | assignment | Feb 1st 2 | 2pm";
        EventTask eventTask = EventTask.dbToEventTask(dbEvent);
        eventTask.markDone();
        System.out.println(eventTask);
        System.out.println(EventTask.eventTaskToDb(eventTask));
    }
}
