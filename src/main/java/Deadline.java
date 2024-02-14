public class Deadline extends Task {
    private String name;
    private String by;
    private boolean done;
    private static String identifier = "[D]";

    public Deadline(String name, String by) {
        super(name);
        this.name = name;
        this.by = by;
        this.done = false;
    }

    public Deadline(String name, String by, boolean done) {
        super(name);
        this.name = name;
        this.by = by;
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
            return "[X]" + identifier + " " + this.name + " (by" + by + ")";
        } else {
            return "[ ]" + identifier + " " + this.name + " (by" + by + ")";
        }

    }

    public String getInput() {
        String mark;
        if (this.done) {
            mark = "1";
        } else {
            mark = "0";
        }
        String str = String.format("%s:deadline:%s:%s", mark, name, by);
        return str;
    }
}
