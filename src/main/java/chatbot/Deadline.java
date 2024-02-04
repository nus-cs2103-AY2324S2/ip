package chatbot;
public class Deadline extends chatbot.Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public String getBy() {
        return this.by;
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
