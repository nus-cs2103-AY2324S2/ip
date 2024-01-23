public class Event extends Task {
    private String start;
    private String end;

    public Event(String taskDescription, String start, String end) {
        super(taskDescription);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                this.isDone ? "X" : " ",
                this.taskDescription,
                this.start,
                this.end
        );
    }
}
