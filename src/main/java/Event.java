public class Event extends Task{

    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }


    @Override
    public String getTaskIcon() {
        return "E";
    }
    @Override
    public String ToString() {
        return super.ToString() + " (from: " + from + "to: " + to + ")";
    }
}
