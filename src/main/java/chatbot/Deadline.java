package chatbot;
public class Deadline extends chatbot.Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {

        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
