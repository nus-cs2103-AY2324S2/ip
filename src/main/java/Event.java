public class Event extends Task {
    private final String begin;
    private final String end;

    public Event(String description, String begin, String end) {
        super(description);
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.begin + " to: " + this.end + ")";
    }
}
