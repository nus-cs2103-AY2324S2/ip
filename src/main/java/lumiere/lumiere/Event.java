package lumiere.lumiere;

public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructor for Event object
     * 
     * @param item   The item that describes what the event is about, i.e. name of
     *               event.
     * @param marked A boolean that indicates whether this event is marked as
     *               attended or not.
     * @param fun    A boolean that indicates whether this event is marked as fun or
     *               not.
     * @param start  A string that indicates when the event starts.
     * @param end    A string that indicates when the event ends.
     * @return Nothing, it is a constructor.
     */
    public Event(String item, boolean marked, boolean fun, String start, String end) {
        super(item, marked, fun);
        this.start = start;
        this.end = end;
    }

    /**
     * An instance method that returns when the start of the event is.
     * 
     * @return A string that represents the start of the event.
     */
    public String getStart() {
        return this.start;
    }

    /**
     * An instance method that returns when the end of the event is.
     * 
     * @return A string that represents the end of the event.
     */
    public String getEnd() {
        return this.end;
    }

    @Override
    public String stringify() {
        String m = "";
        if (super.isMarked())
            m = "[X]";
        else
            m = "[ ]";

        if (super.isFun())
            return "[E]" + m + " " + super.stringify() + " (from: " + this.start + " to: " + this.end + ") #fun";
        else
            return "[E]" + m + " " + super.stringify() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
