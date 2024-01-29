public class Event extends Task {

    protected String deadlineFrom;
    protected String deadlineTo;


    public Event(String description, String deadlineFrom, String deadlineTo) {
        super(description);
        this.deadlineFrom = deadlineFrom;
        this.deadlineTo = deadlineTo;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + deadlineFrom + " to: " + deadlineTo + ")";
    }
}
