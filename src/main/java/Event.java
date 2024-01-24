public class Event extends Task{

    private final String timing;
    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    @Override
    public String toString() {
        if(this.isDone) {
            return "[E][X] "+ description + " (from: " + timing + ")";
        } else {
            return "[E][ ] " + description + " (from: " + timing + ")";
        }
    }
}
