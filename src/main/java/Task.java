public class Task {
    private enum Status {
        Complete("completed", 'X'), Incomplete("pending", ' ');

        private final char icon;
        private final String description;
        Status(String description, char icon) {
            this.icon = icon;
            this.description = description;
        }
    }

    private final String description;
    private Status status;

    public Task(String description) {
        this.description = description;
        this.status = Status.Incomplete;
    }

    public void setComplete() {
        this.status = Status.Complete;
    }

    public void setIncomplete() {
        this.status = Status.Incomplete;
    }

    public String status() {
        return this.status.description;
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
        return "[" + this.status.icon + "] " + description;
    }
}
