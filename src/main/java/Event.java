public class Event extends Task{
    String beginTime;
    String endTime;
    public Event(String description, String beginTime, String endTime) {
        super(description);
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), beginTime, endTime);
    }
}
