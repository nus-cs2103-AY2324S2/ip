package tasks;

public class Task {
    private final String description;
    private boolean isComplete;

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public Task(String description, boolean isComplete) {
        this.description = description;
        this.isComplete = isComplete;
    }

    public void setStatus(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public String stringify() {
        String done = "[ ]";
        if (this.isComplete) {
            done = "[X]";
        }
        return done + " " + this.description;
    }

    public String toString() {
        String done = "O";
        if (this.isComplete) {
            done = "X";
        }
        return done + " | " + this.description;
    }

    /**
     * Searches for keyword in the task description.
     *
     * @param keyword Keyword.
     * @return True if search matches, false otherwise.
     */
    public boolean search(String keyword) {
        if (this.description.contains(keyword)) {
            return true;
        }
        return false;
    }
}