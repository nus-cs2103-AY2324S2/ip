public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String[] arr1 = from.split(" ", 2);
        String[] arr2 = to.split(" ", 2);
        return "[E]" + super.toString() + " (from: " + arr1[1] + " to: " + arr2[1] + ")";
    }
}