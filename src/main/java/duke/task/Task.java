package duke.task;
import java.io.Serializable;

public abstract class Task implements Serializable {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean containsKeyword(String keyword) {
        boolean found = false;
        for (String word : description.split(" ")) {
            if (word.equals(keyword)) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    public String getRepresentation() {
        return this.toString();
    }
}
