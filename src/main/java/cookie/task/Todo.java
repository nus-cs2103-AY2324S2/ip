package cookie.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo object with the given description and tag.
     *
     * @param description The description of the todo task.
     * @param tag The tag of the todo task.
     */
    public Todo(String description, String tag) {
        super(description, tag);
    }

    @Override
    public String toString() {
        String status = getStatusIcon();
        String todoStatus = "[T][" + status + "] ";
        String desc = super.toString();
        String tag = super.tagString();
        return todoStatus + desc + tag;
    }

    @Override
    public String toFileString() {
        String todoStatus = "T | " + (isDone ? "1" : "0");
        String desc = " | " + description;
        String tg = " | " + tag + " | ";
        return todoStatus + desc + tg;
    }
}
