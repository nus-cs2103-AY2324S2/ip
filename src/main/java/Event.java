public class Event extends Task{
    private final String start;
    private final String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, Boolean status, String start, String end) {
        super(description, status);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String convertToFileFormat() {
        return "E | " + super.convertToFileFormat() + " | " + this.start + " | " + this.end;
    }
}
