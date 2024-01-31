package duke.tasks;

public class Event extends Task {
    protected DateTask from;
    protected DateTask to;

    public Event(String description, String from, String to) {
        this(description, from, to , false);
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = new DateTask(from);
        this.to = new DateTask(to);
    }

    @Override
    public String saveFormat() {
        return String.format("%s;;%s;;%s;;%s",
                "E", super.saveFormat(), from.saveFormat(), to.saveFormat());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + 
        String.format(" (from: %s to: %s)", from, to);
    }
}
