package model;

public class Event extends Task {
    public static String typeIcon = "E";
    private String fromDateTime;
    private String toDateTIme;

    public Event(String name, String fromDateTime, String toDateTime) {
        super(name);

        this.fromDateTime = fromDateTime;
        this.toDateTIme = toDateTime;
    }

    public Event(String name, Boolean isCompleted, String fromDateTime, String toDateTime) {
        super(name);

        if (isCompleted) {
            super.markTaskCompleted();
        }

        this.fromDateTime = fromDateTime;
        this.toDateTIme = toDateTime;
    }

    @Override
    public String toTaskListStringFormat() {
        return String.format("%s|%s|%s|%s",
                Event.typeIcon, super.toTaskListStringFormat(),
                this.fromDateTime, this.toDateTIme);
    }

    @Override
    public String getTypeIcon() {
        return Event.typeIcon;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), this.fromDateTime, this.toDateTIme);
    }
}