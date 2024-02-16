package duke;

public class Task {
    protected String description;
    protected Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toTaskSaveString() {
        return this.getStatusInt() + "|" + this.description;
    }

    public int getStatusInt() {
        return this.isDone? 1 : 0;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a boolean that specifies whether the task description contains the keyword.
     *
     * @param keyword Keyword to check for in task description.
     */
    public boolean checkDescription(String keyword) {
        return description.contains(keyword);
    }
}