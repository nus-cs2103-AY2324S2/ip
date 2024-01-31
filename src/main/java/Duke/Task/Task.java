package Duke.Task;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        //Mark done task with X
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String toFile() {
        if (isDone) {
            return "?|1|" + description;
        } else {
            return "?|0|" + description;
        }
    }

    /**
     * Returns whether description contains input.
     *
     * @param input User's Input to find similar tasks.
     * @return True if contains, False otherwise.
     */
    public Boolean contains(String input) {
        return description.contains(input);
    }
}
