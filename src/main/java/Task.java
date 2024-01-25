public class Task {
    private final String description;
    private boolean isDone;
    private final char shortForm;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.shortForm = 'T';
    }

    public Task(String description, char shortForm) {
        this.description = description;
        this.isDone = false;
        this.shortForm = shortForm;
    }

    public String getStatusIcon() {

        return (this.isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%c][%s] %s", this.shortForm, getStatusIcon(), this.description);
    }
}
