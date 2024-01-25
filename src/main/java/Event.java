public class Event extends Task{
    public Event(String input, String start, String end) {
        super(input, start, end);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.getStart() +  " to " + this.getEnd() + ")";
    }

}
