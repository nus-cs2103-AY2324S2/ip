package Gluti.utils;

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

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    public void setDone() {
        isDone = true;
    }

    public void setunDone() { isDone = false; }

    public boolean isMatch(String keyword) {
        return this.description.contains(keyword);
    }
}
