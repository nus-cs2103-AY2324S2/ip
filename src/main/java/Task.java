public class Task {
    private String description;

    public Task(String description) {
        this.description = description;
    }

    /**
     * A more verbose english explanation of the task.
     *
     * @return description of the task
     */
    public String describe() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
