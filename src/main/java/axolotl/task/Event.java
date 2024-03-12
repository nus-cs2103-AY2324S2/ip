package axolotl.task;

import axolotl.parser.Parser;

import java.time.LocalDateTime;

/**
 * An Event object which contains a start date (from) and end date (to)
 */
public class Event extends Task {
    protected LocalDateTime from; // start date
    protected LocalDateTime to; // end date
    static protected String ALIAS = "E"; // alias (i.e. E for Event)

    /**
     * Create an event with following details:
     * @param taskName
     * @param status
     * @param from
     * @param to
     */
    public Event(String taskName, boolean status, LocalDateTime from, LocalDateTime to) {
        super(taskName, status);
        this.from = from;
        this.to = to;
    }

    /**
     * Return the string representation of this event,
     * where status is either 'X' or ' ' and date is in MMM dd yyyy hh:mma
     * @return [E] [<status>] <task_name> (from: <from> to: <to>)
     */
    @Override
    public String toString() {
        return "[" + ALIAS + "]" + super.toString() + " (from: " + from.format(dateTimeString)
                + " to: " + to.format(dateTimeString)  + ")";
    }

    /**
     * Return the string representation of this event to be stored in local file,
     * where the status is in string format (true/false) and date is in dd/MM/yyyy HHmm format.
     * @return E,<status (true/false)>,<task_name>,<from>,<to>
     */
    @Override
    public String toStore() {
        return ALIAS + super.toStore() + "," + from.format(Parser.dateTimeFormatter)
                + "," + to.format(Parser.dateTimeFormatter);
    }
}
