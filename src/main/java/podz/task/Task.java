package podz.task;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

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
     * @param keyword the keyword to check for in the task description
     * @return true if the task description contains the keyword, false otherwise
     */
    public boolean isRelevantTask(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}