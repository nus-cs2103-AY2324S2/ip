package dylanbot;

/**
 * Represents a general Task
 */
public class Task {

    private String type;
    private String desc;
    private boolean completed;

    /**
     * Creates a new Task with the given type and description
     *
     * @param type The specified type
     * @param desc The specified description
     */
    public Task(String type, String desc) {
        this.type = type;
        this.desc = desc;
        this.completed = false;
    }

    public String getType() {
        return this.type;
    }

    public String getDesc() {
        return this.desc;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Marks the task as completed
     */
    public void mark() {
        this.completed = true;
    }

    /**
     * Marks the task as not completed
     */
    public void unmark() {
        this.completed = false;
    }

    @Override
    public String toString() {
        return "[" + type + "] "
                + (completed ? "[X]" : "[ ]")
                + " " + desc;
    }
}

