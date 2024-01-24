public class Task {

    protected boolean isDone;
    protected final String description;
    public Task(String description) {
        isDone = false;
        this.description = description;

    }

    public void complete() {
        this.isDone = true;
    }

    public void incomplete() {this.isDone = false; }
    @Override
    public String toString() {
        if(this.isDone) {
            return "[X] "+ description;
        } else {
            return "[ ] " + description;
        }
    }
}
