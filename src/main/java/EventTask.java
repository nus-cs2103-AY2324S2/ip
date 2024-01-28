public class EventTask extends Task {
    private String from;
    private String to;

    public EventTask(String task) {
        super(task);
        parseEvent(task);
    }

    private void parseEvent(String task) {
        String[] split = task.split("/from", 2);

        if (split.length == 2) {
            this.task = split[0];

            String[] details = split[1].split("/to", 2);
            if (details.length == 2) {
                this.from = details[0].trim();
                this.to = details[1].trim();
            }
        }
    }

    @Override
    public String tag() {
        return "[E]";
    }
    @Override
    public String toString() {
        return tag() + mark() + " " + task + " (from: " + from + " to: " + to + ")";
    }
}