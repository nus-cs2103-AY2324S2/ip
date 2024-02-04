public class Event extends Task {

    protected String deadlineFrom;
    protected String deadlineTo;
    String DIVIDER = " | ";


    public Event(boolean isDone, String description, String deadlineFrom, String deadlineTo) {
        super(description);
        this.deadlineFrom = deadlineFrom;
        this.deadlineTo = deadlineTo;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + deadlineFrom + " to: " + deadlineTo + ")";
    }

    @Override
    public String getFileString() {
        return "E" + DIVIDER + (isDone ? "1" : "0") + DIVIDER + description
                + DIVIDER + deadlineFrom + DIVIDER + deadlineTo;
    }

}
