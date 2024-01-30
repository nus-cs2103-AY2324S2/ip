package drew.tasktypes;

/**
 * This class represents the Event task.
 */
public class Event extends Task{
    private String startDate;
    private String endDate;
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    /**
     * Returns the type, status and description of the Event task.
     * @return String of format [E]['Task Status'] 'Task description'.
     */
    public String statusString() {
        return String.format("[E]%s (from: %s to: %s)", super.statusString(), this.startDate, this.endDate);
    }
    @Override
    public String toSaveFormatString() {
        String status = (super.isDone) ? "1" : "0";
        return String.format("E | %s | %s | %s | %s\n", status, super.toString(), this.startDate, this.endDate);
    }
}
