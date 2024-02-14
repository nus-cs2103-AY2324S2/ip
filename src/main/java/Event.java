public class Event extends Task {
    private String name;
    private String from;
    private String to;
    private boolean done;

    private static String identifier = "[E]";
    public Event(String name, String from, String to) {
        super(name);
        this.name = name;
        this.from = from;
        this.to = to;
        this.done = false;
    }

    @Override
    public String toString() {
        String str = identifier + super.toString() + " (" + from + to + ")";
        return str;
    }

    public String getInput() {
        String str = String.format("event %s /%s /%s", name, from, to);
        return str;
    }

}
