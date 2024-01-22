package Tasks;

public class EventTask extends Task{
    private String start;
    private String end;
    public EventTask(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }
    public String getStatusIcon() {
        return (this.isDone() ? "[E][X] " : "[E][ ] ");
    }

    public String toString() {
        return this.getStatusIcon() + this.getDesc() + " (from: " + start + " to: " + end + ")";
    }
}
