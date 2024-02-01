package action.task;

public class EventTask extends Task{
    private String from;
    private String to;
    public EventTask(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from +
                " to: " + to + ")";
    }
}
