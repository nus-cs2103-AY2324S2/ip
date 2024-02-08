package FriendlyTool.task;

import FriendlyTool.main.Date;

public class Event extends Task {
    private Date from;
    private Date to;
    public Event(String name, boolean isDone, Date from, Date to) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +" (from: " + from + " to: " + to +")";
    }

    @Override
    public String toSaveFormat() {
        return "E " + super.toSaveFormat() + " | " + from.toSaveFormat() + " | " + to.toSaveFormat() + "\n";
    }
}
