public class Task {
    private String task;
    private boolean done;
    public Task(String task) {
        this.task = task;
        this.done = false;
    }
    public void markDone() {
        this.done = true;
        return;
    }
    public void markUndone() {
        this.done = false;
    }
    public boolean done() {
        return this.done;
    }
    public String getStatus() {
        return (this.done) ? "X" : " ";
    }
    @Override
    public String toString() {
        return "["+this.getStatus()+"] "+this.task;
    }
}
