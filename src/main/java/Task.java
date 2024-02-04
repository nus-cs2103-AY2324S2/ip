public class Task {
    private String task;
    private boolean isDone;
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }
    public void markDone() {
        this.isDone = true;
        return;
    }
    public void markUndone() {
        this.isDone = false;
    }
    public boolean done() {
        return this.isDone;
    }
    public String getStatus() {
        return (this.isDone) ? "X" : " ";
    }
    @Override
    public String toString() {
        return "["+this.getStatus()+"] "+this.task;
    }
}
