public class EventTask extends Task {
    private String startTime;
    private String endTime;

    public EventTask(String startTime, String endTime, String task) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
