public abstract class Task {
    protected String description;
    protected boolean isComplete;

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public void markAsDone() {
        isComplete = true;
    }

    public void unmarkAsDone() {
        isComplete = false;
    }

    public String getStatusIcon() {
        return (isComplete ? "X" : " ");
    }

    // Abstract method to be implemented by subclasses
    public abstract String toFileFormat();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }


}
