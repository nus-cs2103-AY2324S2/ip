public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.type = "E";
        this.from = from;
        this.to = to;
    }

    public String getStatus() {
        String statusIcon = (isDone ? "X" : " ");
        return "[" + type + "][" + statusIcon + "] " + description + " (from: " + from + " to: " + to + ")";
    }

    public String toText() {
        return super.toText() + " / " + from + " / " + to;
    }

    static public Task fromText(String description, String done, String from, String to) {
        Task task = new Event(description, from, to);
        task.isDone = done.equals("1");
        return task;
    }
}
