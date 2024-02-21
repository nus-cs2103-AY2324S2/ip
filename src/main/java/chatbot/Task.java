package chatbot;

/**
 * Represents a task in the chatbot.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Constructs a task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public String getDescription() {
        return this.description;
    }
    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns true if the task is done.
     * @return true if the task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }

    /**
     * Returns true if the description contains the keyword.
     * @param keyword the keyword to search for.
     * @return true if the description contains the keyword.
     */
    public boolean descriptionContains(String keyword) {
        return this.getDescription().contains(keyword);
    }
}

