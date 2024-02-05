package chatbot;
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
        return String.format("[%s] %s",this.getStatusIcon(), this.getDescription());
    }
}

