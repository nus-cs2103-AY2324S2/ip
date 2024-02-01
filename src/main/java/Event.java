public class Event extends Task{
    /**
     * Starting time and date of the event.
     */
    private String startDate;

    /**
     * Ending time and date of the event.
     */
    private String endDate;

    /**
     *
     * @param description: Description of the event.
     * @param startDate: Starting date and time of the event.
     * @param endDate: Ending date and time of the event.
     */
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Getter for start date.
     *
     * @return The start date of the event.
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * Getter for end date.
     *
     * @return The end date of the event.
     */
    public String getEndDate() {
        return this.endDate;
    }

    @Override
    public String toFileString() {
        String taskType = "E";
        String isDone = this.getStatus() ? "1" : "0";
        String description = super.toFileString();
        String startDate = this.getStartDate();
        String endDate = this.getEndDate();
        return taskType + " | " + isDone + " | " + description + " | " + startDate + " | " + endDate;
    }

    @Override
    public String toString() {
        String taskType = "[E]";
        String eventString = taskType + super.toString() + " (from: " + this.startDate + " to: " + this.endDate + ")";
        return eventString;
    }

}
