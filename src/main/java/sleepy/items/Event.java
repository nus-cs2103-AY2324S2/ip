package sleepy.items;

/**
 * This class is a type of item.
 *
 * @author kjw142857
 */
public class Event extends Item {
    private String start;
    private String end;
    public Event(String rawDescription, String description, String start, String end) {
        super(rawDescription, description);
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
