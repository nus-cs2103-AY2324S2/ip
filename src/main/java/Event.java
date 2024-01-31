public class Event extends Task{
    String start,deadline;
    public Event(String description, String start, String deadline, boolean done) {
        super(description, done);
        this.start = start;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[E]%s", super.toString());
    }
}
