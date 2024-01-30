public abstract class Task {
    private String name;
    protected boolean isDone;

    Task(String name) {
        this(name, false);
    }

    Task(String name, boolean done) {
        this.name = name;
        this.isDone = done;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    protected String getDescription() {
        return this.name;
    }

    public abstract String fileRepresentation();
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.name);
    }
}
