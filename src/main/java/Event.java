public class Event extends Task {

    private static final String EVENT_MESSAGE = "[E]%s (from: %s to: %s)";
    private String startTime;
    private String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format(EVENT_MESSAGE, super.toString(), startTime, endTime);
    }

}
