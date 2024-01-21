public class EventTask extends Task{

    private String start;
    private String end;
    public EventTask(String name, boolean done, String start, String end) {
        super(name, done);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return " [E]" + super.toString() + " (from: " + this.start + " " + "to: " + end +")";
    }
}
