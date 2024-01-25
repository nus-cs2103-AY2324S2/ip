public abstract class Task {
    private final String name;
    private boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks the task as done or not done.
     *
     * @param done The completion status to set for the task (true if done, false if not done).
     */
    public void markDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "X" : " ", name);
    }
}
