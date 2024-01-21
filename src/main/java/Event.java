public class Event extends Task {
    public Event(String description, String startTime, String endTime) {
        super(description, startTime, endTime, Type.EVENT);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(),
                super.getStartTime(), super.getEndTime());
    }
}
