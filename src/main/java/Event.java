public class Event extends Task {
    protected String from, to;
    protected String taskType = "E";

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    //Overridden toString method to print type of task, description and time
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + from + "to:" + to + ")";
    }

    public String getFrom() {
        return this.from;
    }
    public String getTo() {
        return this.to;
    }

}
