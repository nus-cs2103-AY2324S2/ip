public class Event extends Task {
    String from;
    String to;


    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s from:%s to:%s", super.toString() , from, to);
    }
}
