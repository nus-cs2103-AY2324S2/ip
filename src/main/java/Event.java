public class Event extends Task {

    private static final String TYPE_SYMBOL = "[E]";
    private final String from;
    private final String to;

    public Event(String description, String from, String to) throws MeanDukeException {
        super(description, TYPE_SYMBOL);
        if (from.isEmpty() || to.isEmpty()) {
            throw new MeanDukeException("Usage: \"add event <description> /from <start> /to <end>\"");
        }
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, TYPE_SYMBOL, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + this.from + " - " + this.to + ")";
    }
}
