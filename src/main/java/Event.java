public class Event extends Task {
    private String startTime;
    private String endTime;
    Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    String getCommand() {
        return "event " + name + " /from " + startTime + " /to " + endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
