public class Event extends Task {
    private static final String TYPE_SYMBOL = "E";
    private final String begin;
    private final String end;

    public Event(String description, String begin, String end) {
        super(description);
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toCsv() {
        return TYPE_SYMBOL + "," + (super.getDone() ? "1" : "0") + "," + super.getDescription() + "," + this.begin + "," + this.end;
    }

    @Override
    public String toString() {
        return "[" + TYPE_SYMBOL + "]" + super.toString() + " (from: " + this.begin + " to: " + this.end + ")";
    }
}
