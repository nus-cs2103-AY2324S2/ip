package duke.task;

public class Task {
    private String description;
    private boolean hasDone;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getHasDone() {
        return hasDone;
    }

    public Task() {

    }

    public Task(String description) {
        this.description = description;
        this.hasDone = false;
    }

    public void setHasDone(boolean isDone) {
        this.hasDone = isDone;
    }

    @Override
    public String toString() {
        if (this.hasDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
