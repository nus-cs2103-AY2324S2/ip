package Duke.Task;

/**
 * This class contains the functions for tasks.
 * @author Tang Hao Liang
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to update description for task.
     *
     * @param description Task's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status of the task.
     *
     * @return Task's status.
     */
    public String getStatusIcon() {
        //Mark done task with X
        return (isDone ? "X" : " ");
    }

    /**
     * Marks status to done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks status to undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the text to be saved.
     *
     * @return Text to be saved.
     */
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
