public class Event extends Task {
    private String startTime;
    private String endTime;

    Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s) ", startTime, endTime);
    }
}
