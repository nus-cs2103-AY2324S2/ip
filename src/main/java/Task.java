public class Task {
    enum Status {
        DONE,
        NOT_DONE
    }
    protected String description;
    protected Status status;

    public Task(String description) {
        this.description = description;
        this.status = Status.NOT_DONE;
    }

    public String getStatusIcon() {
        return (status == Status.DONE ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public void setDone(boolean done) {
        this.status = (done ? Status.DONE : Status.NOT_DONE);
    }

}