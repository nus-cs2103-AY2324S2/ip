public class Task {
    private String description;
    private int index;
    private boolean isDone = false;

    public Task(int index, String description) {
        this.index = index;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

}
