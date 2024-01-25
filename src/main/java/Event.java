public class Event extends Item {
    private String start;
    private String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the description of this event.
     *
     * @return Description of this event.
     */
    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (from: " + start + " to: " + end + ")";
    }
}
