package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Events Task.
 *
 * This class is the representation of a Events task.
 * It extends from its parent class the Task class.
 */
public class Event extends Task implements Comparable<Event> {
    public static final String TASK_TYPE = "event";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private String from;
    private String to;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    /**
     * Creates a Events object.
     * Will call the super constructor with the task name variable.
     *
     * @param name The name of the task.
     * @param from The start of the event.
     * @param to The end of the event.
     */
    public Event(String name, String from, String to, LocalDateTime fromDate, LocalDateTime toDate) {
        super(name);
        this.from = from.split("from ")[1];
        this.to = to.split("to ")[1];
        this.fromDateTime = fromDate;
        this.toDateTime = toDate;
    }

    /**
     * Creates a Events object, with a mark variable to indicate if the
     * created object as been marked as complete or not.
     * Will call the super constructor with the task name variable.
     *
     * @param name The name of the task.
     * @param from The start of the event.
     * @param to The end of the event.
     * @param mark Indication of completed or not.
     */
    public Event(String name, String from, String to, String mark) {
        super(name);
        this.from = from.split("from ")[1];
        this.to = to.split("to ")[1];
        this.fromDateTime = LocalDateTime.parse(from.split("from ")[1].trim(), DATE_FORMAT);
        this.toDateTime = LocalDateTime.parse(to.split("to ")[1].trim(), DATE_FORMAT);
        if (mark.equals("1")) {
            super.mark();
        }
    }

    /**
     * Returns a string representation of this Events object for storage in DataWriter.
     * This includes the formating required for the reader to split and read it.
     *
     * @return a formatted string representation of this object.
     */
    @Override
    public String formatDataLine() {
        return "Events|" + super.getCompleted() + "|" + super.getCommand() + "|from " + this.from + "|to " + this.to;
    }

    /**
     * Returns a string representation of this Events.
     * This includes an indicator that this is a Events object.
     *
     * @return a string representation of this Events object.
     */
    @Override
    public String toString() {
        String s = "[E]" + super.toString() + "(from: " + this.fromDateTime.format(DATE_FORMAT) + " to: "
                + this.toDateTime.format(DATE_FORMAT) + ")";
        return s;
    }

    /**
     * Executes the necessary action created from the parsed results.
     * In this case, will add the Event object to the TaskStorage of the application.
     *
     * @param taskStorage The storage space where the action will take place.
     */
    @Override
    public String execute(TaskStorage taskStorage) {
        taskStorage.addTask(this);
        String printMessage = "Gotchu! I've added this task:";
        printMessage += "\n" + this + "\n";
        printMessage += "You now have " + taskStorage.size() + " tasks in the list.";
        return printMessage;
    }

    /**
     * Overrides the compareTo function of Object, to compare the Event's
     * fromDateTime followed by it's name if they have similar from dates.
     *
     * @param o The Event it is comparing to.
     */
    @Override
    public int compareTo(Event o) {
        if (this.fromDateTime.compareTo(o.fromDateTime) == 0) {
            return super.getCommand().compareTo(o.getCommand());
        }
        return this.fromDateTime.compareTo(o.fromDateTime);
    }
}
