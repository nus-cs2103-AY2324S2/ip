public abstract class Task {
    private final String name;
    private boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.done = isDone;
    }

    /**
     * Marks the task as done or not done.
     *
     * @param done The completion status to set for the task (true if done, false if not done).
     */
    public void markDone(boolean done) {
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "X" : " ", name);
    }

    /**
     * Returns a string representation of the task to be written to the file.
     *
     * @return A string representation of the task to be written to the file.
     */
    public abstract String toFileString();
}
