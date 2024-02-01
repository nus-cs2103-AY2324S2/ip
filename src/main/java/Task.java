public class Task {
    private String taskName;
    private boolean done;

    public Task(String taskName, boolean done) {
        this.taskName = taskName;
        this.done = done;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String storeData() {
        return this.taskName + " " + this.done;
    }

    @Override
    public String toString() {
        return this.done
            ? "[X] " + this.taskName
            : "[ ] " + this.taskName;
    }
}
