public class TimeBlock extends Task {
    protected String fromTime;
    protected String toTime;

    public TimeBlock(String description, String fromTime, String toTime) {
        super(description);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromTime + " to: " + this.toTime + ")";
    }
}