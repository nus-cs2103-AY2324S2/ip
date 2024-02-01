package duke.task;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = TimeManager.parseTime(start);
        this.end = TimeManager.parseTime(end);
    }
    @Override
    public String getType(){
        return "[E]";
    }
    @Override
    public String getTime(){
        return "(from: " + start + " to: " + end + ")";
    }

    @Override
    public String getRawTime(){
        return this.start + " to: " + this.end;
    }
}
