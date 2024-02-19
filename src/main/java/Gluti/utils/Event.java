package Gluti.utils;

/**
 * The Event subclass that is a child of Task class
 */
public class Event extends Task {

    protected String[] date;

    /**
     * Initializes a Event instance
     * @param description name of the Event
     * @param date the period of the Event
     */
    public Event(String description, String[] date) {
        super(description);
        this.date = date;
    }

    /**
     * Overloaded constructor for FileStorage tasks mounting
     * @param description name of the Event
     * @param date1 the start period of the Event
     * @param date2 the end period of the Event
     */

    public Event(String description, String date1, String date2) {
        super(description);
        this.date = new String[] {date1,date2};
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + date[0] + "to:"+ date[1] + ")";
    }
}