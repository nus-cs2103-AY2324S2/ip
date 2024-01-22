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

    @Override
    public String toString() {
        String taskType = "[E]";
        String eventString = taskType + super.toString() + " (from: " + this.startDate + " to: " + this.endDate + ")";
        return eventString;
    }

}
