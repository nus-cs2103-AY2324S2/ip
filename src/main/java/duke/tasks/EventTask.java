package duke.tasks;
public class EventTask extends Task{
    private String start;
    private String end;
    public EventTask(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    public EventTask(String desc, String isDone, String start, String end) {
        super(desc, isDone);
        this.start = start;
        this.end = end;
    }
    public String getStatusIcon() {
        return (this.isDone() ? "[E][X] " : "[E][ ] ");
    }

    public String toString() {
        return this.getStatusIcon() + this.getDesc() + " (from: " + start + " to: " + end + ")";
    }
    public String save() {
        String isDone = this.isDone() ? "1" : "0";
        return "E," + isDone + "," + this.getDesc() + "," + this.start + "," + this.end;
    }
}
