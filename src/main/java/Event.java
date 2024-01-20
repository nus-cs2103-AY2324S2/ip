public class Event extends Task {
    public Event(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
