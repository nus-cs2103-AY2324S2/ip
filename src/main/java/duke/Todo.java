package duke;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    private String name;
    private boolean done;
    private final static String IDENTIFIER = "[T]";
    public Todo(String name) {
        super(name);
        this.name = name;
        this.done = false;
    }
    public Todo(String name, boolean done) {
        super(name);
        this.name = name;
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
            return "[X]" + IDENTIFIER + " " + this.name;
        } else {
            return "[ ]" + IDENTIFIER + " " + this.name;
        }
    }

    public String getInput() {
        String mark;
        if (this.done) {
            mark = "1";
        } else {
            mark = "0";
        }
        String str = String.format("%s/todo/%s", mark, name);
        return str;
    }
}
