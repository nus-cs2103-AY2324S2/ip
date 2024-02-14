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

    public Event(String name, String from, String to, boolean done) {
        super(name);
        this.name = name;
        this.from = from;
        this.to = to;
        this.done = done;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X]" + identifier + " " + this.name + " (from" + from + "to" + to + ")";
        } else {
            return "[ ]" + identifier + " " + this.name + " (from" + from + "to" + to + ")";
        }
    }

    public String getInput() {
        String mark;
        if (this.done) {
            mark = "1";
        } else {
            mark = "0";
        }
        String str = String.format("%s:event:%s:%s:%s", mark, name, from, to);
        return str;
    }

}
