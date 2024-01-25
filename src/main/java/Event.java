public class Event extends Task{
    public String startTime;
    public String endTime;

    public Event(String description) {
        super(description);
    }

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String print() {
        String str = "[E]" + "[" + super.getStatusIcon() + "] " + super.description + "(" + "from: " +
                this.startTime + "to: " + this.endTime + ")";
        return str;
    }
}
