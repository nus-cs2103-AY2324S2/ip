package util;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * The CsvUtil class represents a utility class for working with CSV data.
 * It provides methods for constructing CsvUtil objects from CSV strings,
 * converting CsvUtil objects to Task objects, and converting CsvUtil objects
 * to CSV strings.
 */
public class CsvUtil {
    private final String event;
    private final String marked;
    private final String description;
    private final String by;
    private final String from;
    private final String to;

    /**
     * Constructs a CsvUtil object with the specified event, marked, and description.
     * The 'by', 'from', and 'to' fields are set to null.
     *
     * @param event       the event type
     * @param marked      the marked status
     * @param description the description of the event
     */
    public CsvUtil(String event, String marked, String description) {
        this.event = event;
        this.marked = marked;
        this.description = description;
        this.by = null;
        this.from = null;
        this.to = null;
    }

    /**
     * Constructs a CsvUtil object with the specified event, marked, description, and by.
     * The 'from' and 'to' fields are set to null.
     *
     * @param event       the event type
     * @param marked      the marked status
     * @param description the description of the event
     * @param by          the deadline of the event
     */
    public CsvUtil(String event, String marked, String description, String by) {
        this.event = event;
        this.marked = marked;
        this.description = description;
        this.by = by;
        this.from = null;
        this.to = null;
    }

    /**
     * Constructs a CsvUtil object with the specified event, marked, description, from, and to.
     * The 'by' field is set to null.
     *
     * @param event       the event type
     * @param marked      the marked status
     * @param description the description of the event
     * @param from        the start date of the event
     * @param to          the end date of the event
     */
    public CsvUtil(String event, String marked, String description, String from, String to) {
        this.event = event;
        this.marked = marked;
        this.description = description;
        this.by = null;
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a CsvUtil object by parsing the given CSV string.
     *
     * @param csv the CSV string to parse
     */
    public CsvUtil(String csv) {
        String[] vals = csv.split(",");
        this.event = vals[0];
        this.marked = vals[1];
        this.description = vals[2];
        this.by = vals[3].equals("null") || vals[3].isEmpty() ? null : vals[3];
        this.from = vals[4].equals("null") || vals[4].isEmpty() ? null : vals[4];
        this.to = vals[5].equals("null") || vals[5].isEmpty() ? null : vals[5];
    }

    /**
     * Converts the CsvUtil object to a Task object.
     *
     * @return the Task object representing the CsvUtil object
     * @throws IllegalArgumentException if the 'marked' value is invalid or the date format is invalid
     */
    public Task toTask() {
        boolean isMarked;
        switch (marked) {
        case "true":
            isMarked = true;
            break;
        case "false":
            isMarked = false;
            break;
        default:
            throw new IllegalArgumentException("Invalid 'marked' value: " + marked);
        }
        if (!DateTimeUtil.isValid(by) || !DateTimeUtil.isValid(from) || !DateTimeUtil.isValid(to)) {
            throw new IllegalArgumentException("Invalid date format.");
        }

        switch (event) {
        case "T":
            return new ToDo(isMarked, description);
        case "D":
            return new Deadline(isMarked, description, DateTimeUtil.parse(by));
        case "E":
            return new Event(isMarked, description, DateTimeUtil.parse(from),
                    DateTimeUtil.format(to));
        default:
            throw new IllegalArgumentException("Invalid 'event' value: " + event);
        }

    }

    /**
     * Converts the CsvUtil object to a CSV string.
     *
     * @return the CSV string representation of the CsvUtil object
     */
    public String toCsv() {
        return String.format("%s,%s,%s,%s,%s,%s\n", event, marked, description,
                by, from, to);
    }

}
