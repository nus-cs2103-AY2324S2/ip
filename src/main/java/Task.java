public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void mark() {
        this.isDone = true;
    }

    public void unMark(){
        this.isDone = false;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "] " + description; // mark done task with X
    }
}
