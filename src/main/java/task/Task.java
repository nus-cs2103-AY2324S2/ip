package task;

public class Task {
    private boolean done;
    private String taskString;

    public Task(String s) {
        this.taskString = s;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }

    public boolean getDoneStatus() {
        return this.done;
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

    public boolean containsKeyword(String keyword) {
        return this.taskString.contains(keyword);
    }
}
