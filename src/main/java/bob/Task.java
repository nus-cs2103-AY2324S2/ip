package bob;

public abstract class Task {
    private static final String DONE_ICON = "X";
    private static final String NOT_DONE_ICON = " ";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String isDone) {
        this.description = description;
        this.isDone = isDone.equals("1");
    }

    public String getStatusIcon() {
        return (isDone ? DONE_ICON : NOT_DONE_ICON);
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String toStorageFormat() {
        return (isDone ? "true" : "false") + " | " + this.description;
    }

    @Override
    public String toString() {
        return '[' + this.getStatusIcon() + "] " + this.description;
    }
}
