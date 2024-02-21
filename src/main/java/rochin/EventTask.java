package rochin;

public class EventTask extends Task {
    protected String from;
    protected String to;

    /**
     * Construct an EventTask with a description, starting, and ending date/time.
     *
     * @param description The description of the event task.
     * @param from        The starting date/time of the event.
     * @param to          The ending date/time of the event.
     */
    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Return a new event task.
     *
     * @param descriptionWithDate description with date.
     * @return A new event task.
     */
    public EventTask createTask(String descriptionWithDate) throws RochinException {
        String[] parts = descriptionWithDate.split("/from");
        if (parts.length == 2) {
            String description = parts[0].trim();
            String[] dateRange = parts[1].split("/to");
            if (dateRange.length == 2) {
                String from = dateRange[0].trim();
                String to = dateRange[1].trim();
                return new EventTask(description, from, to);
            }
        }
        throw new RochinException("OOPS!!! Please provide a description, start time, and end time for an event task.");
    }

    /**
     * Return a string representation of the task type.
     *
     * @return A string representation of the task type.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Return a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
