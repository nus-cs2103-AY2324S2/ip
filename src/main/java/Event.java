public class Event extends Task{
    private String startTime;
    private String endTime;
    private String type;

    public Event(String description) {
        super(description);
        this.type = "E";
    }

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = "E";
    }

    @Override
    public String print() {
        String str = "[E]" + super.print() + "(from: " +
                this.startTime + "to: " + this.endTime + ")";
        return str;
    }
    @Override
    public String getDescription() {
        String str = "[E] " + super.getDescription() + " " + this.startTime + " " + this.endTime;
        return str;
    }

    @Override
    public String getTaskInfo() {
        return "[E] " + "/ [" + super.getStatusIcon() + "] / " + super.getTaskInfo() + " / " + this.startTime
                + " / " + this.endTime;
    }
}
