package model;

public class Event extends Task {
    private String fromDateTime;
    private String toDateTIme;

    public Event(String name, String fromDateTime, String toDateTIme) {
        super(name);

        this.fromDateTime = fromDateTime;
        this.toDateTIme = toDateTIme;
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), this.fromDateTime, this.toDateTIme);
    }
}
