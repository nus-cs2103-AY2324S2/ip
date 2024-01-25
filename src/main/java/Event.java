

public class Event extends Task {
    String from;
    String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }
    
    @Override
    public String toString() {
        String date = String.format(" (from: %sto: %s)", this.from, this.to);
        return "[E]" + super.toString() + date;
    }
}
