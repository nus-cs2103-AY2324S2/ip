package jojo;

/**
 * Stores a task which is 1 of the 3 types: todo, event and deadline.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string depending on whether the task is done.
     * @return "X" if done, " " if not done
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns a string depending on whether the task is done.
     * @return "1" if done, "0" if not done
     */
    public String getBooleanStatusIcon() {
        return (this.isDone ? "1" : "0");
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Returns a simplified toString for the ease of saving.
     * @return String
     */
    public String simpleToString() {
        return "| " + getBooleanStatusIcon() + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}

