package kai.task;

public class Event extends Task {
    protected String startDate;

    public Event(String description, String startDate) {
        super(description);
        this.startDate = startDate;
    }

    public Event(String description, String startDate, boolean isDone) {
        super(description, isDone);
        this.startDate = startDate;
    }

    /**
     * Returns the event in a new String format when written in a file.
     *
     * @return New String format.
     */
    @Override
    public String formatStringForSaveFile() {
        return "E | " + super.formatStringForSaveFile() + " | " + startDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startDate +  ")";
    }
}
