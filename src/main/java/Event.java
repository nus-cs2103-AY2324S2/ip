

public class Event extends Task {
    String from;
    String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from.substring(5);
        this.to = to.substring(3);
    }
    
    @Override
    public String toString() {
        String date = String.format(" (from: %sto: %s)", this.from, this.to);
        return "[E]" + super.toString() + date;
    }
}
