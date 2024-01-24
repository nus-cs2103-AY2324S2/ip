//// Solution below adapted by week2 iP Level-3 Partial solution
public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String destription, String from, String to) {
        super(destription);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + "to: " + this.to + ")";
    }
}
