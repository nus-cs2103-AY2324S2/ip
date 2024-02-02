public class Task {
    final private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.isDone? "X" : " ",
                this.description);
    }

    // TODO: override this in the 3 task methods to add type
    public String toSavedString() {
        return String.format("%d#!#%s",
                this.isDone? 1 : 0,
                this.description);
    }
}
