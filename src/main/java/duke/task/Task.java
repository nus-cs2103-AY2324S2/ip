package duke.task;

import java.io.Serializable;

public class Task implements Serializable {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done Duke.task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        }
        else {
            return "[ ] " + description;
        }
    }

}
