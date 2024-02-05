public class Event extends Task {
    private String startTime;
    private String endTime;
    public Event(String s, String startTime, String endTime) {
        super(s);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), this.startTime, this.endTime);
    }

    @Override
    public String convertToDataStoreLine() {
        return String.format("E|%s|%s|%s|%s", super.convertToDataStoreLine(),
                super.getTaskString(), this.startTime, this.endTime);
    }
}