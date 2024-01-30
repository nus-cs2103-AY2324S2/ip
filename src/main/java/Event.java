public class Event extends Task {
    private String start;
    private String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }
    @Override
    public String getType(){
        return "[E]";
    }
    @Override
    public String getTime(){
        return " (from: " + start + " to: " + end + ")";
    }

}
