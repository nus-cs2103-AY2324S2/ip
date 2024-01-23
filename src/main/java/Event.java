public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String name, String from, String to) {
        super(name);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
