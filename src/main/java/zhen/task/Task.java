package zhen.task;

import java.io.Serializable;

public class Task implements Serializable {
    private final String message;
    private boolean isCompleted = false;
    protected String tag = "";

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
    public void addTag(String tagInfo) {
        this.tag = this.tag + " #" + tagInfo;
    }
    @Override
    public String toString() {
        if (isCompleted == true) {
            return "[X] " + message + tag;
        } else {
            return "[ ] " + message + tag;
        }
    }
}
