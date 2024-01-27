public class Event extends Task {
    public Event(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone, startTime, endTime, TaskType.EVENT);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(),
                super.getStartTime(), super.getEndTime());
    }
}
