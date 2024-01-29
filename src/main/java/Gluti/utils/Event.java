package Gluti.utils;

public class Event extends Task {

    protected String[] date;

    public Event(String description, String[] date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + date[0] + "to:"+ date[1] + ")";
    }
}