public class Event extends Task {
    private String start;
    private String end;
    public Event(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return String.format("[E]%s(from: %sto: %s)",
                super.toString(),start, end);
    }
}
