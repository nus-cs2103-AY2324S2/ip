public class Event extends Task {

    protected String by1;
    protected String by2;

    public Event(String description, String by1, String by2) {
        super(description);
        this.by1 = by1;
        this.by2 = by2;
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + "(from:" + by1.substring(4) + "to:" + by2.substring(2) + ")";
    }
}
