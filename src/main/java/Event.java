public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String desc) {
        String[] splitDesc = desc.split(" /from ");
        this.description = splitDesc[0];
        String[] splitPeriod = splitDesc[1].split(" /to ");
        this.from = splitPeriod[0];
        this.to = splitPeriod[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + to + ")";
    }
}