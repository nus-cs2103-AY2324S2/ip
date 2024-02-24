package task;

public class Task {
    private boolean isDone;
    private String taskString;

    public Task(String s) {
        this.taskString = s;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }

    public String getTaskString() {
        return this.taskString;
    }

    public String toString() {
        if (this.getDoneStatus()) {
            return String.format("[X] %s", this.taskString);
        } else {
            return String.format("[ ] %s", this.taskString);
        }
    }

    public String convertToDataStoreLine() {
        if (this.getDoneStatus()) {
            return "1";
        } else {
            return "0";
        }
    }
}
