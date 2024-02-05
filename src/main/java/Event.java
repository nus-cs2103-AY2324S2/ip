public class Event extends Task{

    private final String from_time;
    private final String to_time;

    public Event(String description, String from_time, String to_time){
        super(description);
        this.from_time = from_time;
        this.to_time = to_time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from_time + " to: " + to_time + ")";
    }

}
