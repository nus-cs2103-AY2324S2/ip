package podz.task;

/**
 * Represents a task in the task manager.
 */
public class Task {
    private static final String MARKED_STR = "X";
    private static final String UNMARKED_STR = " ";
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     * 
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * 
     * @return the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? MARKED_STR : UNMARKED_STR);
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the saved format of the task.
     * 
     * @return the saved format of the task.
     */
    public String savedFormat() {
        String isDoneStr = " 0 ";
        if (isDone) {
            isDoneStr = " 1 ";
        }

        return isDoneStr + "| " + description;
    }

    /**
     * Returns whether the task description contains the specified keyword.
     *
     * @param keyword the keyword to check for in the task description.
     * @return true if the task description contains the keyword, false otherwise.
     */
    public boolean isRelevantTask(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Returns a string representation of the task.
     * 
     * @return a string representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
