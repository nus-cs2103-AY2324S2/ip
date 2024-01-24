public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
        UI.printMarking(this, this.getTag(), this.isDone);
    }

    public void unmark() {
        this.isDone = false;
        UI.printMarking(this, this.getTag(), this.isDone);
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public String getTag() {
        return "[T]";
    }

    public void printTaskDesc(int num, boolean isLast) {
        UI.printResponse(num, this.getTag(), this, isLast);
    }
}
