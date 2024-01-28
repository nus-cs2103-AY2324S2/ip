public class EventTaskLoad extends Task {
    private String time;
    private String from;
    private String to;

    public EventTaskLoad(String task, String time) {
        super(task);
        parseEventLoad(time);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    private void parseEventLoad(String time) {
        String[] split = time.split("-", 2);

            if (split.length == 2) {
                this.from = split[0].trim();
                this.to = split[1].trim();
            }
    }


    @Override
    public String toString() {
        return tag() + mark() + " " + task + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String tag() {
        return "[E]";
    }
}
