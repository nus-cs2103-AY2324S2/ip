public class Event extends Task {

    private static final String EVENT_MESSAGE = "[E]%s (from: %s to: %s)";
    private static final String EVENT_FILE_TEMPLATE = "E | %s | %s | %s";
    private final String startTime;
    private final String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String taskFileTemplate() {
        return String.format(EVENT_FILE_TEMPLATE, super.taskFileTemplate(), startTime, endTime);
    }

    @Override
    public String toString() {
        return String.format(EVENT_MESSAGE, super.toString(), startTime, endTime);
    }

}
