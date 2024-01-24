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

    public String toString() {
        return this.taskString;
    }
}
