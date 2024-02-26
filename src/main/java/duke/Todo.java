package duke;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    private String name;
    private boolean isDone;
    private Tag tag;
    private final static String IDENTIFIER = "[T]";
    public Todo(String name) {
        super(name);
        this.name = name;
        this.isDone = false;
        this.tag = new Tag();
    }
    public Todo(String name, boolean isDone) {
        super(name);
        this.name = name;
        this.isDone = isDone;
        this.tag = new Tag();

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

    public void addTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X]" + IDENTIFIER + " " + this.name + " " + this.tag.toString();
        } else {
            return "[ ]" + IDENTIFIER + " " + this.name + " " + this.tag.toString();
        }
    }

    public String getInput() {
        String mark;
        if (this.isDone) {
            mark = "1";
        } else {
            mark = "0";
        }
        String str = String.format("%s/todo/%s/%s", mark, name, tag.getInput());
        return str;
    }
}
