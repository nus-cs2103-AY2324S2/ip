public class Event extends Task {
    String label = "[E]";

    public Event(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return label + super.toString() + " ";
    }
}
