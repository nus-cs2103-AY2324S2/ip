public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        isDone = true;
    }
    public void unMark() {
        isDone = false;
    }
    @Override
    public String toString(){
        String s = "[" + this.getStatusIcon() + "] " + this.description;
        return s;
    }
}
