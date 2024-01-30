package lamball.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    public String command() {
        return "How did you get here?";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Checks if the task description contains the given string.
     *
     * @param compare String used for comparison.
     * @return Whether the given string is a sub-sequence of the task.
     */
    public boolean containing(String compare) {
        return description.contains(compare);
    }
}
