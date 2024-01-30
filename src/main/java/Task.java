public abstract class Task {
    protected final String name;
    protected boolean done;

    public Task(boolean done, String name) {
        this.done = done;
        this.name = name;
    }

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsNotDone() {
        this.done = false;
    }

    public abstract String toSavedString();
}
