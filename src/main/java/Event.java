public class Event extends Task {
    private final String from;
    
    private final String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String describe() {
        return String.format("[E]%s (from: %s to: %s)",
                super.describe(), this.from, this.to
        );
    }
}
