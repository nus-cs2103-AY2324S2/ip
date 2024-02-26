package duke;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    private String name;
    private boolean isDone;
    private final static String IDENTIFIER = "[T]";
    public Todo(String name) {
        super(name);
        this.name = name;
        this.isDone = false;
    }
    public Todo(String name, boolean isDone) {
        super(name);
        this.name = name;
        this.isDone = isDone;

    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X]" + IDENTIFIER + " " + this.name;
        } else {
            return "[ ]" + IDENTIFIER + " " + this.name;
        }
    }

    public String getInput() {
        String mark;
        if (this.isDone) {
            mark = "1";
        } else {
            mark = "0";
        }
        String str = String.format("%s/todo/%s", mark, name);
        return str;
    }
}
