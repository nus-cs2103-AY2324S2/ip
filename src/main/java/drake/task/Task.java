package drake.task;

import java.io.Serializable;

public class Task implements Serializable { // adapted skeleton from cs2103t course website
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
