package Gluti.utils;

public class Event extends Task {

    protected String[] date;

    public Event(String description, String[] date) {
        super(description);
        this.date = date;
    }

    public Event(String description, String date1, String date2) {
        super(description);
        this.date = new String[] {date1,date2};
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + date[0] + "to:"+ date[1] + ")";
    }
}