package util;

import task.Task;
import task.Event;
import task.Deadline;
import task.ToDo;

public class CSVUtil {
    private String event;
    private String marked;
    private String description;
    private String by;
    private String from;
    private String to;

    // Constructor for Todo type.
    public CSVUtil(String event, String marked, String description) {
        this.event = event;
        this.marked = marked;
        this.description = description;
        this.by = null;
        this.from = null;
        this.to = null;
    }

    // Constructor for Deadline type.
    public CSVUtil(String event, String marked, String description, String by) {
        this.event = event;
        this.marked = marked;
        this.description = description;
        this.by = by;
        this.from = null;
        this.to = null;
    }

    // Constructor for Event type.
    public CSVUtil(String event, String marked, String description, String from, String to) {
        this.event = event;
        this.marked = marked;
        this.description = description;
        this.by = null;
        this.from = from;
        this.to = to;
    }

    // Constructor for CSV values.
    public CSVUtil(String CSV) {
        String[] vals = CSV.split(",");
        this.event = vals[0];
        this.marked = vals[1];
        this.description = vals[2];
        this.by = vals[3];
        this.from = vals[4];
        this.to = vals[5];
    }

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
        // TODO: check for valid datetime format.
        switch(event) {
        case "T":
            return new ToDo(isMarked, description);
        case "D":
            return new Deadline(isMarked, description, by);
        case "E":
            return new Event(isMarked, description, from, to);
        default:
            throw new IllegalArgumentException("Invalid 'event' value: " + event);
        }

    }

    public String toCSV() {
        return String.format("%s,%s,%s,%s,%s,%s\n", event, marked, description, by, from, to);
    }

}
