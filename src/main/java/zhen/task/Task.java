package zhen.task;

import java.io.Serializable;

public class Task implements Serializable {
    private final String message;
    private boolean isCompleted = false;

    public Task(String message) {
        this.message = message;
        if (message.length() == 0) {
            throw new IllegalArgumentException("input can't be empty");
        }
    }
    public void mark() {
        isCompleted = true;
    }

    public void unmark() {
        isCompleted = false;
    }
    protected boolean isCompleted() {
        return isCompleted;
    }
    public String getMessage() {
        return message;
    }
    @Override
    public String toString() {
        if (isCompleted == true) {
            return "[X] " + message;
        } else {
            return "[ ] " + message;
        }
    }
}
