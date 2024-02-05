package ChatbotRan;

public abstract class Task {
    protected String contents;
    protected boolean completed;

    public Task(String contents) {
        this.contents = contents;
        this.completed = false;
    }

    abstract String getType();

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    abstract String writeTask();

    @Override
    public String toString() {
        return "[" + this.getType() + "][" + (this.completed ? "X" : " ") + "] " + this.contents;
    }

}
