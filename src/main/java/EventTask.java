public class EventTask extends Task {
    private final String  startTime;
    private final String  endTime;

    public EventTask(String startTime, String endTime, String task) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
    @Override
    public String logString(){return 'E' + super.logString() + '|' + startTime +'|' + endTime;}
}
