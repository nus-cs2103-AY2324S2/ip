package fishstock;

abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected void markAsDone() {
        this.isDone = true;
    }

    protected void markAsUndone() {
        this.isDone = false;
    }

    protected abstract String toSaveString();

    protected int boolToInt(boolean bool) {
        return bool ? 1 : 0;
    }
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}