public class Event extends Task {

    private static final String TYPE = "E";
    protected String start;
    protected String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ":::" + this.start + ":::" + this.end;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() +
                "(from: " + start + "to: " + end + ")";
    }
}
